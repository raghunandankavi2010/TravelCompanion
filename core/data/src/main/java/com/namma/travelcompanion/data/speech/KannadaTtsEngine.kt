package com.namma.travelcompanion.data.speech

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class KannadaTtsEngine(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)
    private val _isReady = MutableStateFlow(false)
    val isReady: StateFlow<Boolean> = _isReady.asStateFlow()

    private val _isSpeaking = MutableStateFlow(false)
    val isSpeaking: StateFlow<Boolean> = _isSpeaking.asStateFlow()

    private val _isKannadaSupported = MutableStateFlow(true)
    val isKannadaSupported: StateFlow<Boolean> = _isKannadaSupported.asStateFlow()

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val kannadaLocale = Locale("kn", "IN")
            val result = tts?.setLanguage(kannadaLocale)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                val fallbackResult = tts?.setLanguage(Locale("en", "IN"))
                _isKannadaSupported.value = (fallbackResult != TextToSpeech.LANG_MISSING_DATA && fallbackResult != TextToSpeech.LANG_NOT_SUPPORTED)
            } else {
                _isKannadaSupported.value = true
            }

            tts?.setSpeechRate(0.92f)
            tts?.setPitch(1.0f)

            tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    _isSpeaking.value = true
                }

                override fun onDone(utteranceId: String?) {
                    _isSpeaking.value = false
                }

                @Deprecated("Deprecated in Java")
                override fun onError(utteranceId: String?) {
                    _isSpeaking.value = false
                }
            })

            _isReady.value = true
        } else {
            Log.e("KannadaTtsEngine", "TTS initialization failed with status $status")
            _isReady.value = false
        }
    }

    fun speak(text: String, utteranceId: String = "kannada_phrase") {
        if (!_isReady.value || tts == null) return
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
    }

    fun stop() {
        tts?.stop()
        _isSpeaking.value = false
    }

    fun release() {
        tts?.stop()
        tts?.shutdown()
        tts = null
        _isReady.value = false
    }
}
