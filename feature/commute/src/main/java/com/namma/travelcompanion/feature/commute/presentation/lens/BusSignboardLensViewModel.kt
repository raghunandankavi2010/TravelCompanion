package com.namma.travelcompanion.feature.commute.presentation.lens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namma.travelcompanion.feature.commute.data.analyzer.BusSignLensAnalyzer
import com.namma.travelcompanion.data.speech.KannadaTtsEngine
import com.namma.travelcompanion.data.translator.OnDeviceTranslator
import com.namma.travelcompanion.data.translator.TranslationModelState
import android.graphics.Bitmap
import com.namma.travelcompanion.feature.commute.domain.model.SignTextBlock
import com.namma.travelcompanion.feature.commute.domain.model.SignTextBlocks
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withTimeoutOrNull
import com.namma.travelcompanion.feature.commute.domain.model.BusRouteInfo
import com.namma.travelcompanion.feature.commute.domain.model.BusSignLensDatabase
import com.namma.travelcompanion.feature.commute.domain.model.BusSignLensScanResult
import com.namma.travelcompanion.feature.commute.domain.model.ConductorPhrase
import com.namma.travelcompanion.feature.commute.domain.model.DetectedSignboardInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class LensViewMode {
    LIVE_CAMERA,
    ROUTE_DIRECTORY,
    SIGN_TRANSLATE
}

/** One Kannada block on the frozen sign and its English, null until translated (or if the model is missing). */
data class TranslatedSignBlock(val block: SignTextBlock, val english: String?)

sealed interface SignReadState {
    data object Aiming : SignReadState
    data object Reading : SignReadState
    data class Frozen(val frame: Bitmap, val blocks: List<TranslatedSignBlock>) : SignReadState
    data class Failed(val message: String) : SignReadState
}

class BusSignboardLensViewModel(
    val analyzer: BusSignLensAnalyzer,
    private val ttsEngine: KannadaTtsEngine,
    initialMode: LensViewMode = LensViewMode.LIVE_CAMERA
) : ViewModel() {

    val scanResult: StateFlow<BusSignLensScanResult> = analyzer.scanFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BusSignLensScanResult())

    val isSpeaking = ttsEngine.isSpeaking

    private val _isTorchEnabled = MutableStateFlow(false)
    val isTorchEnabled: StateFlow<Boolean> = _isTorchEnabled.asStateFlow()

    private val _isAutoAnnounceEnabled = MutableStateFlow(true)
    val isAutoAnnounceEnabled: StateFlow<Boolean> = _isAutoAnnounceEnabled.asStateFlow()

    private val _viewMode = MutableStateFlow(initialMode)
    val viewMode: StateFlow<LensViewMode> = _viewMode.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedRouteDetails = MutableStateFlow<BusRouteInfo?>(null)
    val selectedRouteDetails: StateFlow<BusRouteInfo?> = _selectedRouteDetails.asStateFlow()

    /** Opened straight into sign mode (its own route): back leaves the screen instead of returning to bus lens. */
    val openedInSignMode = initialMode == LensViewMode.SIGN_TRANSLATE

    private val _signState = MutableStateFlow<SignReadState>(SignReadState.Aiming)
    val signState: StateFlow<SignReadState> = _signState.asStateFlow()
    val translationModelState: StateFlow<TranslationModelState> = OnDeviceTranslator.modelState
    private var signJob: Job? = null

    private var lastAnnouncedIdentifier: String? = null
    private var lastAnnounceTimeMs: Long = 0L

    init {
        analyzer.signMode = initialMode == LensViewMode.SIGN_TRANSLATE
        viewModelScope.launch { OnDeviceTranslator.refreshModelState() }
        viewModelScope.launch {
            scanResult.collect { result ->
                if (_isAutoAnnounceEnabled.value) {
                    val currentRoute = result.detectedRoute
                    val currentSign = result.detectedSignboard
                    val now = System.currentTimeMillis()

                    if (currentRoute != null && (currentRoute.routeNumber != lastAnnouncedIdentifier || now - lastAnnounceTimeMs > 12000L)) {
                        lastAnnouncedIdentifier = currentRoute.routeNumber
                        lastAnnounceTimeMs = now
                        playRouteAnnouncement(currentRoute)
                    } else if (currentSign != null && (currentSign.id != lastAnnouncedIdentifier || now - lastAnnounceTimeMs > 12000L)) {
                        lastAnnouncedIdentifier = currentSign.id
                        lastAnnounceTimeMs = now
                        playSignboardAnnouncement(currentSign)
                    }
                }
            }
        }
    }

    fun toggleTorch() {
        _isTorchEnabled.value = !_isTorchEnabled.value
    }

    fun toggleAutoAnnounce() {
        _isAutoAnnounceEnabled.value = !_isAutoAnnounceEnabled.value
    }

    fun setViewMode(mode: LensViewMode) {
        _viewMode.value = mode
        analyzer.signMode = mode == LensViewMode.SIGN_TRANSLATE
        resumeSignCamera()
    }

    /** Tap on the live sign view: freeze the next frame, OCR its Kannada, translate each block. */
    fun freezeSign() {
        if (signJob?.isActive == true) return
        signJob = viewModelScope.launch {
            _signState.value = SignReadState.Reading
            val frame = withTimeoutOrNull(15_000L) { analyzer.readSignFrame() }
            if (frame == null) {
                _signState.value = SignReadState.Failed("Couldn't read Kannada on that frame. Hold steady and tap again.")
                return@launch
            }
            _signState.value = SignReadState.Frozen(frame.bitmap, SignTextBlocks.merge(frame.lines).map { TranslatedSignBlock(it, null) })
            translateFrozenSign()
        }
    }

    private suspend fun translateFrozenSign() {
        val frozen = _signState.value as? SignReadState.Frozen ?: return
        val translated = frozen.copy(blocks = frozen.blocks.map {
            it.copy(english = it.english ?: OnDeviceTranslator.translate(it.block.text, OnDeviceTranslator.KANNADA, OnDeviceTranslator.ENGLISH))
        })
        _signState.update { if (it === frozen) translated else it } // user may have moved on meanwhile
    }

    fun resumeSignCamera() {
        signJob?.cancel()
        _signState.value = SignReadState.Aiming
    }

    /** Only after the user agreed in the prompt; ML Kit waits for Wi-Fi. */
    fun downloadTranslationModel() {
        viewModelScope.launch {
            if (OnDeviceTranslator.downloadModel()) translateFrozenSign()
        }
    }

    fun speakSignBlock(block: TranslatedSignBlock) {
        ttsEngine.speak(block.block.text, "sign_block")
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectRoute(route: BusRouteInfo?) {
        _selectedRouteDetails.value = route
    }

    fun playRouteAnnouncement(route: BusRouteInfo) {
        // First speak Kannada announcement, or English if preferred
        val speech = "${route.voiceAnnouncementKn} ${route.voiceAnnouncementEn}"
        ttsEngine.speak(speech, "bus_route_${route.routeNumber}")
    }

    fun playSignboardAnnouncement(sign: DetectedSignboardInfo) {
        val speech = "${sign.kannadaOriginal}. ${sign.englishTitle}. ${sign.travelerAdvice}"
        ttsEngine.speak(speech, "signboard_${sign.id}")
    }

    fun speakConductorPhrase(phrase: ConductorPhrase) {
        ttsEngine.speak(phrase.kannadaLipi, "phrase_${phrase.id}")
    }

    fun stopAudio() {
        ttsEngine.stop()
    }

    fun simulateRoute(routeNumber: String) {
        analyzer.simulateRouteScan(routeNumber)
    }

    fun simulateSignboard(signId: String) {
        analyzer.simulateSignboardScan(signId)
    }

    fun clearDetection() {
        analyzer.clearScan()
        lastAnnouncedIdentifier = null
    }

    override fun onCleared() {
        super.onCleared()
        analyzer.close()
        ttsEngine.release()
    }
}
