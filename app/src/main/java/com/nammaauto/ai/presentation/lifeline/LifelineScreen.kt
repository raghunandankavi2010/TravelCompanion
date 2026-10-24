package com.nammaauto.ai.presentation.lifeline

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nammaauto.ai.core.theme.AutoGreen
import com.nammaauto.ai.core.theme.AutoYellow
import com.nammaauto.ai.core.theme.FairMeterGreen
import com.nammaauto.ai.core.theme.FastMeterAlert
import com.nammaauto.ai.core.theme.GoldPro
import com.nammaauto.ai.core.theme.MetroPurple
import com.nammaauto.ai.core.theme.MonsoonBlue
import com.nammaauto.ai.core.theme.Slate200
import com.nammaauto.ai.core.theme.Slate400
import com.nammaauto.ai.core.theme.Slate700
import com.nammaauto.ai.core.theme.Slate800
import com.nammaauto.ai.core.theme.Slate900
import com.nammaauto.ai.domain.model.AirportBusRoute
import com.nammaauto.ai.domain.model.EmergencyContact
import com.nammaauto.ai.domain.model.HelplineCategory
import com.nammaauto.ai.domain.model.HospitalInfo
import com.nammaauto.ai.presentation.components.BangaloreBadge
import com.nammaauto.ai.presentation.components.GlassCard

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
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Bengaluru Lifeline Hub",
                    color = Slate200,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "24x7 Emergency, Hospitals & City Survival Hacks",
                    color = Slate400,
                    fontSize = 12.sp
                )
            }
            BangaloreBadge(
                text = "CITIZEN SOS",
                badgeColor = FastMeterAlert,
                textColor = Slate200
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Horizontal Category Navigation Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            HelplineCategory.values().forEach { cat ->
                FilterChip(
                    selected = selectedTab == cat,
                    onClick = { viewModel.selectTab(cat) },
                    label = { Text(cat.label, fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = if (cat == HelplineCategory.POLICE_SAFETY) FastMeterAlert else AutoYellow,
                        selectedLabelColor = if (cat == HelplineCategory.POLICE_SAFETY) Slate200 else Slate900
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedTab) {
            HelplineCategory.POLICE_SAFETY, HelplineCategory.CIVIC_UTILITIES -> {
                // Emergency Hotline Cards
                val contacts = viewModel.emergencyContacts.filter { it.category == selectedTab }
                contacts.forEach { contact ->
                    EmergencyContactCard(
                        contact = contact,
                        onDial = { viewModel.dialContact(context, contact.phoneNumber) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            HelplineCategory.HOSPITALS -> {
                // 24x7 Ambulance Quick Banner
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    color = FastMeterAlert.copy(alpha = 0.15f),
                    border = BorderStroke(1.dp, FastMeterAlert)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("FREE EMERGENCY AMBULANCE", color = FastMeterAlert, fontSize = 11.sp, fontWeight = FontWeight.Black)
                            Text("Dial 108 (Arogya Kavacha)", color = Slate200, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                        Button(
                            onClick = { viewModel.dialContact(context, "108") },
                            colors = ButtonDefaults.buttonColors(containerColor = FastMeterAlert),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Call 108", fontWeight = FontWeight.Black, color = Slate200)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "TOP 24x7 TRAUMA HOSPITALS BY ZONE",
                    color = Slate400,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                viewModel.traumaHospitals.forEach { hospital ->
                    HospitalCard(
                        hospital = hospital,
                        onDial = { viewModel.dialContact(context, hospital.emergencyPhone) },
                        onOpenMaps = { viewModel.openHospitalInMaps(context, hospital.mapsQuery) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            HelplineCategory.TRANSIT_HACKS -> {
                // Airport Transit Hero Card
                GlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    borderColor = MonsoonBlue.copy(alpha = 0.6f)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.DirectionsBus, contentDescription = null, tint = MonsoonBlue, modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Airport Vayu Vajra AC Bus Hacks",
                                color = MonsoonBlue,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Save ₹1,500+! Never pay ₹1,800 for airport taxis. BMTC Vayu Vajra Volvos run 24x7 with luggage bays, AC, and high frequency directly into major tech hubs:",
                            color = Slate200,
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                viewModel.busRoutes.forEach { route ->
                    AirportBusCard(route = route)
                    Spacer(modifier = Modifier.height(10.dp))
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Majestic & Yeshwanthpur Prepaid Counter Guide
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    color = Slate800,
                    border = BorderStroke(1.dp, AutoYellow.copy(alpha = 0.4f))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "OFFICIAL PREPAID AUTO BOOTHS (NO HAGGLING)",
                            color = AutoYellow,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "• Majestic Station: Exit Platform 1 gate. Traffic police booth gives a fixed receipt (Govt rate + ₹2 fee). Driver cannot refuse!\n• Yeshwanthpur Station: Counter located at main concourse entry.\n• Cantonment Station: Outside main portico.",
                            color = Slate200,
                            fontSize = 12.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            HelplineCategory.BTP_COMPLAINT -> {
                // Official BTP Auto Grievance Drafter
                GlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    borderColor = FastMeterAlert.copy(alpha = 0.6f)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Report, contentDescription = null, tint = FastMeterAlert, modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Lodge BTP Auto Overcharging Complaint",
                                color = FastMeterAlert,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Auto driver demanded double meter, refused to ply, or harassed you? Submit direct evidence to Bengaluru Traffic Police WhatsApp helpline (9480801000):",
                            color = Slate200,
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = autoNumber,
                            onValueChange = { viewModel.setAutoNumber(it) },
                            label = { Text("Auto Registration No. (e.g., KA-04-AB-1234)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = AutoYellow,
                                unfocusedBorderColor = Slate700,
                                focusedTextColor = Slate200,
                                unfocusedTextColor = Slate200
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = incidentLocation,
                            onValueChange = { viewModel.setIncidentLocation(it) },
                            label = { Text("Incident Location (e.g., Near Indiranagar Metro)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = AutoYellow,
                                unfocusedBorderColor = Slate700,
                                focusedTextColor = Slate200,
                                unfocusedTextColor = Slate200
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text("Select Violation Type:", color = Slate400, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))

                        val violationOptions = listOf(
                            "Demanded excess fare / refused meter",
                            "Refused to go to destination",
                            "Fast / tampered meter detected by AI",
                            "Rude behavior or harassment"
                        )

                        violationOptions.forEach { option ->
                            FilterChip(
                                selected = violationType == option,
                                onClick = { viewModel.setViolationType(option) },
                                label = { Text(option, fontSize = 11.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = FastMeterAlert.copy(alpha = 0.3f),
                                    selectedLabelColor = Slate200
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = {
                                val success = viewModel.sendBtpWhatsAppComplaint(context)
                                if (!success) {
                                    Toast.makeText(context, "Please enter the auto registration number first", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = FastMeterAlert),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = null, tint = Slate200)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Send to BTP WhatsApp (9480801000)", color = Slate200, fontWeight = FontWeight.Black)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ICONIC BENGALURU ETIQUETTE & SLANG GUIDE
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = Slate800,
            border = BorderStroke(1.dp, Slate700)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "BENGALURU STREET SURVIVAL ETIQUETTE",
                    color = GoldPro,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                viewModel.slangGuides.forEach { slang ->
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(slang.kannadaLipi, color = AutoYellow, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("(${slang.phonetic})", color = Slate200, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                        }
                        Text("“${slang.meaning}” — ${slang.culturalContext}", color = Slate400, fontSize = 11.sp)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))
        LegalDisclaimerCard()

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun EmergencyContactCard(
    contact: EmergencyContact,
    onDial: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Slate800,
        border = BorderStroke(1.dp, if (contact.isPrimarySos) FastMeterAlert.copy(alpha = 0.7f) else Slate700)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = contact.title,
                        color = if (contact.isPrimarySos) Slate200 else Slate200,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = contact.subtitle,
                    color = Slate400,
                    fontSize = 11.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "📞 ${contact.phoneNumber}  •  ${contact.operatingHours}",
                    color = AutoYellow,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = onDial,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (contact.isPrimarySos) FastMeterAlert else AutoGreen
                ),
                shape = CircleShape,
                modifier = Modifier.size(46.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Call",
                    tint = if (contact.isPrimarySos) Slate200 else Slate900,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun HospitalCard(
    hospital: HospitalInfo,
    onDial: () -> Unit,
    onOpenMaps: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Slate800,
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = hospital.name,
                        color = Slate200,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Zone: ${hospital.zone}",
                        color = MonsoonBlue,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(hospital.address, color = Slate400, fontSize = 11.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("🏥 ${hospital.specialty}", color = FairMeterGreen, fontSize = 11.sp)

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onDial,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = FastMeterAlert),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Call, contentDescription = null, tint = Slate200, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Emergency", fontSize = 11.sp, color = Slate200, fontWeight = FontWeight.Bold)
                    }
                }

                OutlinedButton(
                    onClick = onOpenMaps,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, AutoYellow)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Map, contentDescription = null, tint = AutoYellow, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Google Maps", fontSize = 11.sp, color = AutoYellow, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun AirportBusCard(route: AirportBusRoute) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Slate800,
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BangaloreBadge(
                        text = route.routeCode,
                        badgeColor = MonsoonBlue,
                        textColor = Slate900
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "to ${route.destination}",
                        color = Slate200,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "₹${route.fareRupees}",
                    color = FairMeterGreen,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text("Stops: " + route.majorStops.joinToString(" ➔ "), color = Slate400, fontSize = 11.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("Frequency: ${route.frequency}", color = AutoYellow, fontSize = 11.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("💰 ${route.comparisonWithCab}", color = FairMeterGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun LegalDisclaimerCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Slate800.copy(alpha = 0.5f),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "LEGAL & FAIR USE DISCLAIMER",
                color = Slate400,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "NammaAuto AI is an independent utility. All trademarks, service marks, and brand names (Uber, Ola, Rapido, Namma Yatri, BTP) are property of their respective owners. Their reference herein is strictly for descriptive nominative comparison under Section 30 of the Trade Marks Act, 1999 and does not imply endorsement or affiliation.",
                color = Slate400,
                fontSize = 10.sp,
                lineHeight = 14.sp
            )
        }
    }
}
