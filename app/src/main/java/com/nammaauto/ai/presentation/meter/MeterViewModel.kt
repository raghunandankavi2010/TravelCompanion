package com.nammaauto.ai.presentation.meter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nammaauto.ai.NammaAutoApplication
import com.nammaauto.ai.data.analyzer.AutoMeterImageAnalyzer
import com.nammaauto.ai.data.location.GpsTelemetryService
import com.nammaauto.ai.domain.model.MeterReading
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MeterViewModel(application: Application) : AndroidViewModel(application) {

    val analyzer = AutoMeterImageAnalyzer()
    val gpsService = GpsTelemetryService(application)
    val billingManager = (application as NammaAutoApplication).billingManager

    val meterReading: StateFlow<MeterReading> = analyzer.readingFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MeterReading())

    val tripState = gpsService.tripState

    val subscriptionState = billingManager.subscriptionState

    init {
        // Feed live GPS distance updates into the OCR analyzer so it benchmarks the meter in real-time
        viewModelScope.launch {
            tripState.collect { trip ->
                analyzer.updateCurrentGpsDistance(trip.distanceKm)
            }
        }
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

    override fun onCleared() {
        super.onCleared()
        analyzer.close()
        gpsService.stopTrip()
    }
}
