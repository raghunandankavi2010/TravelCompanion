package com.namma.travelcompanion.presentation.safety

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namma.travelcompanion.domain.model.BangaloreHub
import com.namma.travelcompanion.domain.model.BangaloreLocations
import com.namma.travelcompanion.data.location.GpsTelemetryService
import com.namma.travelcompanion.data.repository.EmergencyContactStore
import com.namma.travelcompanion.domain.model.DetourDetector
import com.namma.travelcompanion.domain.model.NightCorridorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NightGuardianViewModel(
    private val gpsService: GpsTelemetryService,
    private val emergencyContactStore: EmergencyContactStore,
    private val onDetourAlert: (message: String) -> Unit
) : ViewModel() {

    val tripState = gpsService.tripState
    val emergencyContactPhone: StateFlow<String> = emergencyContactStore.phone

    val hubs = BangaloreLocations.hubs
    private val detourDetector = DetourDetector()
    val detourToleranceMeters = detourDetector.toleranceMeters

    private val _destination = MutableStateFlow(hubs[3]) // HSR Layout
    val destination: StateFlow<BangaloreHub> = _destination.asStateFlow()

    private val _corridorState = MutableStateFlow(
        NightCorridorState(
            isMonitoringActive = false,
            destinationName = hubs[3].name,
            safetyStatusMessage = "Ready. Pick your drop point and start before boarding."
        )
    )
    val corridorState: StateFlow<NightCorridorState> = _corridorState.asStateFlow()

    init {
        viewModelScope.launch {
            tripState.collect { trip ->
                val lat = trip.lastKnownLocationLat ?: return@collect
                val lng = trip.lastKnownLocationLng ?: return@collect
                if (!_corridorState.value.isMonitoringActive) return@collect
                val dest = _destination.value
                val toDestinationMeters = BangaloreLocations.haversineKm(lat, lng, dest.lat, dest.lng) * 1000
                val deviation = detourDetector.update(toDestinationMeters)
                val isDetour = detourDetector.isDetour(deviation)
                if (isDetour && !_corridorState.value.isOffRouteAlert) {
                    onDetourAlert("${deviation.toInt()} m further from ${dest.name} than your closest point. Open the app to send SOS.")
                }
                _corridorState.update {
                    it.copy(
                        currentDeviationMeters = deviation,
                        isOffRouteAlert = isDetour,
                        safetyStatusMessage = if (isDetour) {
                            "Moving AWAY from ${dest.name}: ${deviation.toInt()} m further than your closest point. Ask the driver, or share your location."
                        } else {
                            "Heading towards ${dest.name}. ${(toDestinationMeters / 1000).format1()} km to go (straight line)."
                        }
                    )
                }
            }
        }
    }

    fun setDestination(hub: BangaloreHub) {
        _destination.value = hub
        detourDetector.reset()
        _corridorState.update { it.copy(destinationName = hub.name, currentDeviationMeters = 0.0, isOffRouteAlert = false) }
    }

    fun toggleMonitoring() {
        if (!_corridorState.value.isMonitoringActive) {
            detourDetector.reset()
            gpsService.startTrip()
            _corridorState.update {
                it.copy(isMonitoringActive = true, safetyStatusMessage = "Watching your GPS. You will be alerted if the ride moves away from your drop point.")
            }
        } else {
            gpsService.stopTrip()
            _corridorState.update {
                it.copy(isMonitoringActive = false, isOffRouteAlert = false, currentDeviationMeters = 0.0, safetyStatusMessage = "Monitoring stopped. Stay safe.")
            }
        }
    }

    fun saveEmergencyContact(phone: String) = emergencyContactStore.save(phone)

    /** Never invents a location: without a GPS fix the message says so instead. */
    fun sosMessage(): String {
        val trip = tripState.value
        val lat = trip.lastKnownLocationLat
        val lng = trip.lastKnownLocationLng
        val where = if (lat != null && lng != null) {
            "My location: https://maps.google.com/?q=$lat,$lng"
        } else {
            "My GPS location is not available yet. Please call me."
        }
        return "EMERGENCY: I need help. I am in an auto/cab going to ${_destination.value.name}. $where"
    }

    override fun onCleared() {
        super.onCleared()
        gpsService.stopTrip()
    }

    private fun Double.format1() = "%.1f".format(this)
}
