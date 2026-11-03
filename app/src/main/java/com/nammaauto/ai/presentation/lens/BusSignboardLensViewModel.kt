package com.nammaauto.ai.presentation.lens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nammaauto.ai.data.analyzer.BusSignLensAnalyzer
import com.nammaauto.ai.data.speech.KannadaTtsEngine
import com.nammaauto.ai.domain.model.BusRouteInfo
import com.nammaauto.ai.domain.model.BusSignLensDatabase
import com.nammaauto.ai.domain.model.BusSignLensScanResult
import com.nammaauto.ai.domain.model.ConductorPhrase
import com.nammaauto.ai.domain.model.DetectedSignboardInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class LensViewMode {
    LIVE_CAMERA,
    ROUTE_DIRECTORY
}

class BusSignboardLensViewModel(application: Application) : AndroidViewModel(application) {

    val analyzer = BusSignLensAnalyzer()
    private val ttsEngine = KannadaTtsEngine(application)

    val scanResult: StateFlow<BusSignLensScanResult> = analyzer.scanFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BusSignLensScanResult())

    val isSpeaking = ttsEngine.isSpeaking

    private val _isTorchEnabled = MutableStateFlow(false)
    val isTorchEnabled: StateFlow<Boolean> = _isTorchEnabled.asStateFlow()

    private val _isAutoAnnounceEnabled = MutableStateFlow(true)
    val isAutoAnnounceEnabled: StateFlow<Boolean> = _isAutoAnnounceEnabled.asStateFlow()

    private val _viewMode = MutableStateFlow(LensViewMode.LIVE_CAMERA)
    val viewMode: StateFlow<LensViewMode> = _viewMode.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedRouteDetails = MutableStateFlow<BusRouteInfo?>(null)
    val selectedRouteDetails: StateFlow<BusRouteInfo?> = _selectedRouteDetails.asStateFlow()

    private var lastAnnouncedIdentifier: String? = null
    private var lastAnnounceTimeMs: Long = 0L

    init {
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
