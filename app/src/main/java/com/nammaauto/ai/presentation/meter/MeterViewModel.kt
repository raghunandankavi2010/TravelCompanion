package com.nammaauto.ai.presentation.meter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nammaauto.ai.NammaAutoApplication
import com.nammaauto.ai.data.analyzer.AutoMeterImageAnalyzer
import com.nammaauto.ai.data.location.GpsTelemetryService
import com.nammaauto.ai.data.speech.KannadaTtsEngine
import com.nammaauto.ai.domain.model.AppMarketBenchmark
import com.nammaauto.ai.domain.model.BangaloreTariff
import com.nammaauto.ai.domain.model.MeterReading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MeterViewModel(application: Application) : AndroidViewModel(application) {

    val analyzer = AutoMeterImageAnalyzer()
    val gpsService = GpsTelemetryService(application)
    val billingManager = (application as NammaAutoApplication).billingManager
    private val ttsEngine = KannadaTtsEngine(application)

    val meterReading: StateFlow<MeterReading> = analyzer.readingFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MeterReading())

    val tripState = gpsService.tripState
    val subscriptionState = billingManager.subscriptionState

    private val _isPhoneMeterMode = MutableStateFlow(true) // Default to Phone Meter since most autos don't use meter!
    val isPhoneMeterMode: StateFlow<Boolean> = _isPhoneMeterMode.asStateFlow()

    private val _appBenchmark = MutableStateFlow(BangaloreTariff.calculateAppMarketBenchmark(1.5))
    val appBenchmark: StateFlow<AppMarketBenchmark> = _appBenchmark.asStateFlow()

    init {
        viewModelScope.launch {
            tripState.collect { trip ->
                analyzer.updateCurrentGpsDistance(trip.distanceKm)
                val dist = if (trip.distanceKm > 0.1) trip.distanceKm else 1.5
                _appBenchmark.value = BangaloreTariff.calculateAppMarketBenchmark(dist)
            }
        }
    }

    fun setPhoneMeterMode(isPhoneMeter: Boolean) {
        _isPhoneMeterMode.value = isPhoneMeter
    }

    fun startTrip() {
        gpsService.startTrip()
    }

    fun stopTrip() {
        gpsService.stopTrip()
    }

    fun resetTrip() {
        gpsService.resetTrip()
    }

    fun simulateDistance(addKm: Double) {
        gpsService.simulateDistanceProgress(addKm)
    }

    fun simulateMeterScan(fare: Double) {
        analyzer.simulateDetectedReading(fare, tripState.value.distanceKm)
    }

    fun playNoMeterKannadaOffer() {
        val benchmark = _appBenchmark.value
        ttsEngine.speak(benchmark.recommendedKannadaLipi, "no_meter_offer")
    }

    override fun onCleared() {
        super.onCleared()
        analyzer.close()
        gpsService.stopTrip()
        ttsEngine.release()
    }
}
