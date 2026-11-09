package com.namma.travelcompanion.feature.commute.presentation.meter

import androidx.lifecycle.ViewModel
import com.namma.travelcompanion.data.billing.BillingManager
import androidx.lifecycle.viewModelScope
import com.namma.travelcompanion.feature.commute.data.analyzer.AutoMeterImageAnalyzer
import com.namma.travelcompanion.data.location.GpsTelemetryService
import com.namma.travelcompanion.data.speech.KannadaTtsEngine
import com.namma.travelcompanion.domain.model.AppMarketBenchmark
import com.namma.travelcompanion.domain.model.BangaloreTariff
import com.namma.travelcompanion.feature.commute.domain.model.MeterReading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MeterViewModel(
    val analyzer: AutoMeterImageAnalyzer,
    private val gpsService: GpsTelemetryService,
    private val billingManager: BillingManager,
    private val ttsEngine: KannadaTtsEngine
) : ViewModel() {

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

    /** Opening the camera scanner uses one free daily scan. @return false when the free scans are used up. */
    fun setPhoneMeterMode(isPhoneMeter: Boolean): Boolean {
        if (!isPhoneMeter && _isPhoneMeterMode.value && !billingManager.consumeScanQuota()) return false
        _isPhoneMeterMode.value = isPhoneMeter
        return true
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
