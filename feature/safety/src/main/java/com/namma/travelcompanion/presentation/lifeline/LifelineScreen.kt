package com.namma.travelcompanion.presentation.lifeline

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.HelplineCategory
import com.namma.travelcompanion.core.ui.components.dialNumber
import com.namma.travelcompanion.core.ui.components.openMapsSearch
import com.namma.travelcompanion.core.ui.components.sendWhatsAppOrSms
import com.namma.travelcompanion.presentation.lifeline.components.BtpComplaintCard
import com.namma.travelcompanion.presentation.lifeline.components.EmergencyContactCard
import com.namma.travelcompanion.presentation.lifeline.components.HelplineCategoryChips
import com.namma.travelcompanion.presentation.lifeline.components.HospitalsTab
import com.namma.travelcompanion.presentation.lifeline.components.LegalDisclaimerCard
import com.namma.travelcompanion.presentation.lifeline.components.LifelineHeader
import com.namma.travelcompanion.presentation.lifeline.components.StreetEtiquetteCard
import com.namma.travelcompanion.presentation.lifeline.components.TransitHacksTab

@Composable
fun LifelineScreen(viewModel: LifelineViewModel) {
    val context = LocalContext.current
    val selectedTab by viewModel.selectedTab.collectAsState()
    val autoNumber by viewModel.autoNumber.collectAsState()
    val violationType by viewModel.violationType.collectAsState()
    val incidentLocation by viewModel.incidentLocation.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        LifelineHeader()

        Spacer(modifier = Modifier.height(14.dp))

        HelplineCategoryChips(
            selectedTab = selectedTab,
            onSelect = { viewModel.selectTab(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedTab) {
            HelplineCategory.POLICE_SAFETY, HelplineCategory.CIVIC_UTILITIES -> {
                // Emergency Hotline Cards
                val contacts = viewModel.emergencyContacts.filter { it.category == selectedTab }
                contacts.forEach { contact ->
                    EmergencyContactCard(
                        contact = contact,
                        onDial = { dialNumber(context, contact.phoneNumber) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            HelplineCategory.HOSPITALS -> {
                HospitalsTab(
                    hospitals = viewModel.traumaHospitals,
                    onCallAmbulance = { dialNumber(context, "108") },
                    onDialHospital = { dialNumber(context, it.emergencyPhone) },
                    onOpenMaps = { openMapsSearch(context, it.mapsQuery) }
                )
            }

            HelplineCategory.TRANSIT_HACKS -> {
                TransitHacksTab(routes = viewModel.busRoutes)
            }

            HelplineCategory.BTP_COMPLAINT -> {
                BtpComplaintCard(
                    autoNumber = autoNumber,
                    onAutoNumberChange = { viewModel.setAutoNumber(it) },
                    incidentLocation = incidentLocation,
                    onIncidentLocationChange = { viewModel.setIncidentLocation(it) },
                    violationType = violationType,
                    onViolationTypeChange = { viewModel.setViolationType(it) },
                    onSend = {
                        val message = viewModel.btpComplaintMessage()
                        if (message == null) {
                            Toast.makeText(context, "Please enter the auto registration number first", Toast.LENGTH_SHORT).show()
                        } else {
                            sendWhatsAppOrSms(context, LifelineViewModel.BTP_WHATSAPP_NUMBER, message)
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        StreetEtiquetteCard(slangGuides = viewModel.slangGuides)

        Spacer(modifier = Modifier.height(14.dp))
        LegalDisclaimerCard()

        Spacer(modifier = Modifier.height(24.dp))
    }
}
