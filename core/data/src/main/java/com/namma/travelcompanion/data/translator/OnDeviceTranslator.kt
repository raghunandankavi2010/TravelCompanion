package com.namma.travelcompanion.data.translator

import com.google.android.gms.tasks.Task
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.TranslateRemoteModel
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.ConcurrentHashMap
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

enum class TranslationModelState { CHECKING, NOT_DOWNLOADED, DOWNLOADING, READY, FAILED }

/**
 * ML Kit on-device Kannada <-> English. No server, no key, works offline once the Kannada model
 * (~30 MB; English ships with ML Kit) is on the device. Nothing downloads on its own: callers ask the
 * user, then call [downloadModel], which only fetches over Wi-Fi. ML Kit is process-wide, so this is too.
 */
object OnDeviceTranslator {
    const val KANNADA = TranslateLanguage.KANNADA
    const val ENGLISH = TranslateLanguage.ENGLISH

    private val kannadaModel = TranslateRemoteModel.Builder(KANNADA).build()
    private val clients = ConcurrentHashMap<Pair<String, String>, Translator>()

    private val _modelState = MutableStateFlow(TranslationModelState.CHECKING)
    val modelState: StateFlow<TranslationModelState> = _modelState.asStateFlow()

    suspend fun refreshModelState() {
        if (_modelState.value == TranslationModelState.DOWNLOADING) return
        _modelState.value = runCatching { RemoteModelManager.getInstance().isModelDownloaded(kannadaModel).await() }
            .fold({ if (it) TranslationModelState.READY else TranslationModelState.NOT_DOWNLOADED }, { TranslationModelState.FAILED })
    }

    /** Call only after the user agreed. On mobile data the task waits until Wi-Fi is available. */
    suspend fun downloadModel(): Boolean {
        _modelState.value = TranslationModelState.DOWNLOADING
        val ok = runCatching {
            RemoteModelManager.getInstance()
                .download(kannadaModel, DownloadConditions.Builder().requireWifi().build())
                .await()
        }.isSuccess
        _modelState.value = if (ok) TranslationModelState.READY else TranslationModelState.FAILED
        return ok
    }

    /** kn->en or en->kn. Null when the Kannada model isn't downloaded yet or translation failed; never downloads. */
    suspend fun translate(text: String, from: String, to: String): String? {
        if (text.isBlank() || from == to) return text
        if (_modelState.value != TranslationModelState.READY) refreshModelState()
        if (_modelState.value != TranslationModelState.READY) return null
        val client = clients.getOrPut(from to to) {
            Translation.getClient(TranslatorOptions.Builder().setSourceLanguage(from).setTargetLanguage(to).build())
        }
        return runCatching { client.translate(text).await() }.getOrNull()?.trim()?.takeIf { it.isNotEmpty() }
    }
}

private suspend fun <T> Task<T>.await(): T = suspendCancellableCoroutine { cont ->
    addOnSuccessListener { cont.resume(it) }
    addOnFailureListener { cont.resumeWithException(it) }
    addOnCanceledListener { cont.cancel() }
}
