package com.namma.travelcompanion.presentation.safety

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.ui.components.dialNumber
import com.namma.travelcompanion.core.ui.components.rememberLocationPermissionGate
import com.namma.travelcompanion.core.ui.components.sendSms
import com.namma.travelcompanion.core.ui.components.shareText
import com.namma.travelcompanion.presentation.safety.components.CorridorStatusCard
import com.namma.travelcompanion.presentation.safety.components.DetourAlertBanner
import com.namma.travelcompanion.presentation.safety.components.GuardianHeader
import com.namma.travelcompanion.presentation.safety.components.SosSection
import com.namma.travelcompanion.presentation.safety.components.TelemetryGrid

@Composable
fun NightGuardianScreen(
    viewModel: NightGuardianViewModel
) {
    val context = LocalContext.current
    val corridor by viewModel.corridorState.collectAsStateWithLifecycle()
    val trip by viewModel.tripState.collectAsStateWithLifecycle()
    val destination by viewModel.destination.collectAsStateWithLifecycle()
    val savedContact by viewModel.emergencyContactPhone.collectAsStateWithLifecycle()
    var contactInput by remember(savedContact) { mutableStateOf(savedContact) }
    val withLocationPermission = rememberLocationPermissionGate()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        GuardianHeader(isMonitoringActive = corridor.isMonitoringActive)

        Spacer(modifier = Modifier.height(16.dp))

        CorridorStatusCard(
            corridor = corridor,
            destination = destination,
            hubs = viewModel.hubs,
            onDestinationSelected = viewModel::setDestination,
            onToggleMonitoring = {
                if (corridor.isMonitoringActive) viewModel.toggleMonitoring()
                else withLocationPermission { viewModel.toggleMonitoring() }
            }
        )

        Spacer(modifier = Modifier.height(14.dp))

        DetourAlertBanner(corridor = corridor)

        Spacer(modifier = Modifier.height(14.dp))

        TelemetryGrid(
            speedKmh = trip.currentSpeedKmh.toInt(),
            corridor = corridor,
            detourToleranceMeters = viewModel.detourToleranceMeters.toInt()
        )

        Spacer(modifier = Modifier.height(16.dp))

        SosSection(
            contactInput = contactInput,
            savedContact = savedContact,
            onContactInputChange = { contactInput = it },
            onSaveContact = { viewModel.saveEmergencyContact(contactInput) },
            onSendSos = {
                val message = viewModel.sosMessage()
                if (savedContact.isNotBlank()) sendSms(context, savedContact, message)
                else shareText(context, message, "Send SOS via")
            },
            onCall112 = { dialNumber(context, "112") }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
