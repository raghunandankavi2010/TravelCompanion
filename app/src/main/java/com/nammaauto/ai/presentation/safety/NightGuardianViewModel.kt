package com.nammaauto.ai.presentation.safety

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.nammaauto.ai.core.util.BangaloreHub
import com.nammaauto.ai.core.util.BangaloreLocations
import com.nammaauto.ai.data.location.GpsTelemetryService
import com.nammaauto.ai.domain.model.NightCorridorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NightGuardianViewModel(application: Application) : AndroidViewModel(application) {

    val gpsService = GpsTelemetryService(application)
    val tripState = gpsService.tripState

    val hubs = BangaloreLocations.hubs

    private val _destination = MutableStateFlow(hubs[3]) // HSR Layout
    val destination: StateFlow<BangaloreHub> = _destination.asStateFlow()

    private val _corridorState = MutableStateFlow(
        NightCorridorState(
            isMonitoringActive = false,
            destinationName = hubs[3].name,
            safetyStatusMessage = "Ready. Tap 'Activate Night Corridor' before boarding auto."
        )
    )
    val corridorState: StateFlow<NightCorridorState> = _corridorState.asStateFlow()

    fun setDestination(hub: BangaloreHub) {
        _destination.value = hub
        _corridorState.value = _corridorState.value.copy(destinationName = hub.name)
    }

    fun toggleMonitoring() {
        val nextActive = !_corridorState.value.isMonitoringActive
        if (nextActive) {
            gpsService.startTrip()
            _corridorState.value = _corridorState.value.copy(
                isMonitoringActive = true,
                safetyStatusMessage = "Corridor Active. AI is monitoring GPS bearing & stopping anomalies."
            )
        } else {
            gpsService.stopTrip()
            _corridorState.value = _corridorState.value.copy(
                isMonitoringActive = false,
                isOffRouteAlert = false,
                safetyStatusMessage = "Monitoring stopped. Stay safe."
            )
        }
    }

    fun triggerSimulatedDetour() {
        _corridorState.value = _corridorState.value.copy(
            isOffRouteAlert = true,
            currentDeviationMeters = 680.0,
            safetyStatusMessage = "DETOUR ALERT: Vehicle deviated 680m from route corridor!"
        )
    }

    fun broadcastSosMessage(context: Context, contactPhone: String = "9480801000") {
        val lat = tripState.value.lastKnownLocationLat ?: 12.9255
        val lng = tripState.value.lastKnownLocationLng ?: 77.6844
        val mapsUrl = "https://maps.google.com/?q=$lat,$lng"
        val message = "EMERGENCY: I am riding a Bangalore auto to ${_destination.value.name}. My current live GPS location is: $mapsUrl. Please monitor!"

        val smsIntent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("sms:$contactPhone")
            putExtra("sms_body", message)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(smsIntent)
    }

    fun dialBangalorePoliceHelpline(context: Context) {
        val dialIntent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:9480801000")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(dialIntent)
    }

    override fun onCleared() {
        super.onCleared()
        gpsService.stopTrip()
    }
}
