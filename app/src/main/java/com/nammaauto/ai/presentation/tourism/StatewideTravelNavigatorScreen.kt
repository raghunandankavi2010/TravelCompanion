package com.nammaauto.ai.presentation.tourism

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Train
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nammaauto.ai.core.theme.AutoGreen
import com.nammaauto.ai.core.theme.AutoYellow
import com.nammaauto.ai.core.theme.FastMeterAlert
import com.nammaauto.ai.core.theme.MetroPurple
import com.nammaauto.ai.core.theme.MonsoonBlue
import com.nammaauto.ai.core.theme.Slate200
import com.nammaauto.ai.core.theme.Slate400
import com.nammaauto.ai.core.theme.Slate700
import com.nammaauto.ai.core.theme.Slate800
import com.nammaauto.ai.core.theme.Slate900
import com.nammaauto.ai.core.theme.SurgeWarningOrange
import com.nammaauto.ai.domain.model.OfficialGuideGuideline
import com.nammaauto.ai.domain.model.SeasonTravelInfo
import com.nammaauto.ai.domain.model.StatewideCorridorTransit
import com.nammaauto.ai.domain.model.StatewideTransportData
import com.nammaauto.ai.domain.model.TrainOption
import com.nammaauto.ai.domain.model.TransportModeComparison
import com.nammaauto.ai.presentation.components.GlassCard

enum class NavigatorTab(val label: String, val iconTag: String) {
    LIVE_TRANSIT("Live Bus, Train & Private", "⚡"),
    COMPARE_TRANSPORT("Fleet Comparison", "🚆"),
    TICKET_PORTALS("Where to Buy", "🎟️"),
    BEST_SEASONS("Best Seasons", "🌤️"),
    GUIDES_INFO("Guides & Audio", "👨‍🏫")
}

@Composable
fun StatewideTravelNavigatorScreen(
    onNavigateToBusLens: () -> Unit = {}
) {
    val context = LocalContext.current
    var selectedTab by remember { mutableStateOf(NavigatorTab.LIVE_TRANSIT) }
    var selectedCorridorFilter by remember { mutableStateOf("All") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Karnataka Travel Navigator",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate200
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "🚆", fontSize = 22.sp)
                    }
                    Text(
                        text = "Real-Time KSRTC • Indian Railways • Private Sleepers • Fares",
                        fontSize = 12.sp,
                        color = AutoYellow
                    )
                }
            }

            // Quick AI Bus & Signboard Lens Action Card
            item {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Slate800,
                    border = BorderStroke(1.dp, AutoYellow.copy(alpha = 0.6f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNavigateToBusLens() }
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = AutoYellow.copy(alpha = 0.2f),
                                modifier = Modifier.size(42.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(text = "📷", fontSize = 20.sp)
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Namma Vision Lens",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Slate200
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = AutoGreen.copy(alpha = 0.25f)
                                    ) {
                                        Text(
                                            text = "ON-DEVICE AI",
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = AutoGreen,
                                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "Scan Kannada bus boards, route numbers & platform signs",
                                    fontSize = 11.sp,
                                    color = Slate400
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.Default.DirectionsBus,
                            contentDescription = "Open Lens",
                            tint = AutoYellow,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }

            // Tabs Selector Horizontal Scroll
            item {
                val scrollState = rememberScrollState()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(scrollState),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    NavigatorTab.values().forEach { tab ->
                        val isSelected = selectedTab == tab
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) AutoYellow else Slate800,
                            border = BorderStroke(1.dp, if (isSelected) AutoYellow else Slate700),
                            modifier = Modifier.clickable { selectedTab = tab }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = tab.iconTag, fontSize = 14.sp)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = tab.label,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Slate900 else Slate200
                                )
                            }
                        }
                    }
                }
            }

            // Content based on selected tab
            when (selectedTab) {
                NavigatorTab.LIVE_TRANSIT -> {
                    // Quick Official Portals Strip
                    item {
                        LiveOfficialPortalsHub(
                            onOpenUrl = { openBrowserUrl(context, it) },
                            onCall = { number ->
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
                                context.startActivity(intent)
                            }
                        )
                    }

                    // Corridor Filter Chips
                    item {
                        Column {
                            Text(
                                text = "SELECT TRAVEL CORRIDOR:",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate400,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                val filterOptions = listOf("All", "Mysuru", "Hampi", "Mangaluru", "Coorg", "Gokarna", "Hubballi")
                                filterOptions.forEach { filter ->
                                    val isSelected = selectedCorridorFilter == filter
                                    FilterChip(
                                        selected = isSelected,
                                        onClick = { selectedCorridorFilter = filter },
                                        label = { Text(filter, fontSize = 11.sp) },
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = AutoYellow,
                                            selectedLabelColor = Slate900,
                                            containerColor = Slate800,
                                            labelColor = Slate200
                                        )
                                    )
                                }
                            }
                        }
                    }

                    // List of Corridor Transit Cards
                    val displayedCorridors = if (selectedCorridorFilter == "All") {
                        StatewideTransportData.corridorTransitList
                    } else {
                        StatewideTransportData.corridorTransitList.filter {
                            it.routeTitle.contains(selectedCorridorFilter, ignoreCase = true) ||
                                    it.destinationCity.contains(selectedCorridorFilter, ignoreCase = true)
                        }
                    }

                    items(displayedCorridors, key = { it.id }) { corridor ->
                        CorridorTransitCard(
                            corridor = corridor,
                            onOpenUrl = { openBrowserUrl(context, it) }
                        )
                    }
                }

                NavigatorTab.COMPARE_TRANSPORT -> {
                    item {
                        GlassCard(
                            backgroundColor = Slate800.copy(alpha = 0.95f),
                            borderColor = MonsoonBlue.copy(alpha = 0.5f)
                        ) {
                            Column {
                                Text(
                                    text = "💡 Statewide Transit Master Verdict:",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AutoYellow
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = """• For Western Ghats & Mountain safety: Choose KSRTC (trained drivers, no breakdown abandonment).
• For Coastal & Nature scenery: Choose South Western Railway Vistadome train (Sakleshpur glass coach).
• For Wildlife Safaris: Choose Govt Jungle Lodges (exclusive core-zone tiger permits).
• For Spontaneous night trips: Choose Private Sleepers (VRL/SRS) via RedBus.""",
                                    fontSize = 11.sp,
                                    color = Slate200,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }

                    items(StatewideTransportData.transportComparisons, key = { it.id }) { item ->
                        TransportComparisonCard(
                            item = item,
                            onOpenBooking = { openBrowserUrl(context, item.bookingUrl) }
                        )
                    }
                }

                NavigatorTab.TICKET_PORTALS -> {
                    item {
                        Text(
                            text = "OFFICIAL & VERIFIED BOOKING PORTALS",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate400,
                            letterSpacing = 1.sp
                        )
                    }

                    items(StatewideTransportData.transportComparisons, key = { "portal_" + it.id }) { item ->
                        TicketPortalCard(
                            item = item,
                            onOpenUrl = { openBrowserUrl(context, item.bookingUrl) }
                        )
                    }
                }

                NavigatorTab.BEST_SEASONS -> {
                    item {
                        Text(
                            text = "KARNATAKA SEASONAL CLIMATE & TIMING GUIDE",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate400,
                            letterSpacing = 1.sp
                        )
                    }

                    items(StatewideTransportData.seasonalTravelGuides, key = { it.seasonId }) { season ->
                        SeasonGuideCard(season = season)
                    }
                }

                NavigatorTab.GUIDES_INFO -> {
                    item {
                        GlassCard(
                            backgroundColor = Slate800.copy(alpha = 0.95f),
                            borderColor = AutoGreen.copy(alpha = 0.5f)
                        ) {
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Verified, contentDescription = null, tint = AutoGreen, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Official Ministry of Tourism Guide Verification",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AutoYellow
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Government-approved tourist guides MUST carry an official laminated photo identity card on a green or blue lanyard with hologram issued by the Archaeological Survey of India or Karnataka State Tourism Development Corporation. Reject unauthorized touts claiming entrance is closed or offering 'private view rooms'.",
                                    fontSize = 11.sp,
                                    color = Slate200,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }

                    items(StatewideTransportData.guideGuidelines, key = { it.id }) { guide ->
                        GuideGuidelineCard(guide = guide)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun LiveOfficialPortalsHub(
    onOpenUrl: (String) -> Unit,
    onCall: (String) -> Unit
) {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        borderColor = AutoYellow.copy(alpha = 0.6f),
        backgroundColor = Slate800.copy(alpha = 0.95f)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "⚡", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Official Live Transit Tracking Hub",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate200
                    )
                }
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = AutoGreen.copy(alpha = 0.2f)
                ) {
                    Text(
                        text = "REAL-TIME",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoGreen,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Quick Tracking Action Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                OutlinedButton(
                    onClick = { onOpenUrl("https://ksrtc.in") },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, FastMeterAlert),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = FastMeterAlert)
                ) {
                    Icon(Icons.Default.DirectionsBus, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("KSRTC Awatar GPS", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                OutlinedButton(
                    onClick = { onOpenUrl("https://enquiry.indianrail.gov.in") },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, MetroPurple),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MetroPurple)
                ) {
                    Icon(Icons.Default.Train, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("NTES Train Live Status", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                OutlinedButton(
                    onClick = { onOpenUrl("https://www.irctc.co.in") },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, MonsoonBlue),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MonsoonBlue)
                ) {
                    Text("IRCTC Tickets", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                OutlinedButton(
                    onClick = { onOpenUrl("https://utsonmobile.indianrail.gov.in") },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, AutoGreen),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = AutoGreen)
                ) {
                    Text("UTS (0-Queue General)", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                OutlinedButton(
                    onClick = { onOpenUrl("https://www.redbus.in") },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, SurgeWarningOrange),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SurgeWarningOrange)
                ) {
                    Text("RedBus Private Fares", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 24x7 Transit Helplines
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Slate900,
                border = BorderStroke(1.dp, Slate700)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { onCall("08049596666") }
                    ) {
                        Icon(Icons.Default.Call, contentDescription = null, tint = FastMeterAlert, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "KSRTC 24x7: 080-49596666", fontSize = 10.sp, color = Slate200, fontWeight = FontWeight.Bold)
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { onCall("139") }
                    ) {
                        Icon(Icons.Default.Call, contentDescription = null, tint = MetroPurple, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Railways (RailMadad): 139", fontSize = 10.sp, color = Slate200, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun CorridorTransitCard(
    corridor: StatewideCorridorTransit,
    onOpenUrl: (String) -> Unit
) {
    var selectedSubMode by remember { mutableStateOf(0) } // 0 = Govt Bus, 1 = Train, 2 = Private Bus

    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        cornerRadius = 16.dp,
        borderColor = AutoYellow.copy(alpha = 0.5f)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header Row: Route title & duration
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = corridor.routeTitle,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoYellow
                    )
                    Text(
                        text = "${corridor.distanceKm} km • ${corridor.approxTravelTime}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Slate400
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Slate900,
                    border = BorderStroke(1.dp, Slate700)
                ) {
                    Text(
                        text = "LIVE RADAR",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoGreen,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            // Route Advisory Banner
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
                color = Slate900,
                border = BorderStroke(1.dp, Slate700)
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = MonsoonBlue,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = corridor.liveRouteAdvisory,
                        fontSize = 10.sp,
                        color = Slate200,
                        lineHeight = 14.sp
                    )
                }
            }

            // Mode Selector Buttons (Govt Bus, Railways, Private)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedSubMode = 0 },
                    shape = RoundedCornerShape(8.dp),
                    color = if (selectedSubMode == 0) FastMeterAlert.copy(alpha = 0.25f) else Slate800,
                    border = BorderStroke(1.dp, if (selectedSubMode == 0) FastMeterAlert else Slate700)
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.DirectionsBus, contentDescription = null, tint = FastMeterAlert, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Govt KSRTC",
                            fontSize = 11.sp,
                            fontWeight = if (selectedSubMode == 0) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedSubMode == 0) Slate200 else Slate400
                        )
                    }
                }

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedSubMode = 1 },
                    shape = RoundedCornerShape(8.dp),
                    color = if (selectedSubMode == 1) MetroPurple.copy(alpha = 0.25f) else Slate800,
                    border = BorderStroke(1.dp, if (selectedSubMode == 1) MetroPurple else Slate700)
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Train, contentDescription = null, tint = MetroPurple, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Railways (SWR)",
                            fontSize = 11.sp,
                            fontWeight = if (selectedSubMode == 1) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedSubMode == 1) Slate200 else Slate400
                        )
                    }
                }

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedSubMode = 2 },
                    shape = RoundedCornerShape(8.dp),
                    color = if (selectedSubMode == 2) SurgeWarningOrange.copy(alpha = 0.25f) else Slate800,
                    border = BorderStroke(1.dp, if (selectedSubMode == 2) SurgeWarningOrange else Slate700)
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Speed, contentDescription = null, tint = SurgeWarningOrange, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Private Buses",
                            fontSize = 11.sp,
                            fontWeight = if (selectedSubMode == 2) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedSubMode == 2) Slate200 else Slate400
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Submode Details Display
            when (selectedSubMode) {
                0 -> {
                    // Govt KSRTC Info
                    val bus = corridor.govtBus
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Departure Hub: ${bus.departureHub}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate200
                            )
                            if (bus.shaktiSchemeApplicable) {
                                Surface(shape = RoundedCornerShape(4.dp), color = AutoGreen.copy(alpha = 0.2f)) {
                                    Text("Shakti Free for Women", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = AutoGreen, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
                                }
                            }
                        }

                        Text(text = "Frequency: ${bus.frequency}", fontSize = 11.sp, color = Slate400)

                        // Fleet Breakdown Table
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Slate900,
                            border = BorderStroke(1.dp, Slate700),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                bus.fleetOptions.forEach { opt ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(text = "• ${opt.first} (${opt.second})", fontSize = 11.sp, color = Slate200)
                                        Text(text = opt.third, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoYellow)
                                    }
                                }
                            }
                        }

                        Text(text = "💡 Advice: ${bus.travelerTip}", fontSize = 10.sp, color = Slate400, lineHeight = 14.sp)

                        Button(
                            onClick = { onOpenUrl(bus.portalUrl) },
                            colors = ButtonDefaults.buttonColors(containerColor = FastMeterAlert),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.OpenInBrowser, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Official KSRTC Booking & Live Awatar GPS", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate900)
                        }
                    }
                }
                1 -> {
                    // Railways (SWR) Info
                    val rail = corridor.railway
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "Stations: ${rail.stationNames}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate200
                        )

                        // Train Options List
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Slate900,
                            border = BorderStroke(1.dp, Slate700),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                rail.keyTrains.forEach { train ->
                                    Column {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(text = "#${train.trainNumber} ${train.trainName}", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = if (train.isVandeBharatOrVistadome) AutoYellow else Slate200)
                                                if (train.isVandeBharatOrVistadome) {
                                                    Spacer(modifier = Modifier.width(4.dp))
                                                    Surface(shape = RoundedCornerShape(4.dp), color = AutoYellow.copy(alpha = 0.2f)) {
                                                        Text("PREMIUM", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = AutoYellow, modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp))
                                                    }
                                                }
                                            }
                                            Text(text = train.travelDuration, fontSize = 10.sp, color = Slate400)
                                        }
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(text = "Dep: ${train.departureTime} ➔ Arr: ${train.arrivalTime}", fontSize = 10.sp, color = Slate400)
                                            Text(text = train.classFares, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = AutoGreen)
                                        }
                                        HorizontalDivider(color = Slate800, modifier = Modifier.padding(top = 4.dp))
                                    }
                                }
                            }
                        }

                        Text(text = "📱 UTS Mobile: ${rail.utsMobileAdvice}", fontSize = 10.sp, color = AutoGreen)
                        Text(text = "💡 Tip: ${rail.travelerTip}", fontSize = 10.sp, color = Slate400, lineHeight = 14.sp)

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Button(
                                onClick = { onOpenUrl(rail.liveTrackingUrl) },
                                colors = ButtonDefaults.buttonColors(containerColor = MetroPurple),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("NTES Live Status", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate900)
                            }
                            Button(
                                onClick = { onOpenUrl(rail.irctcBookingUrl) },
                                colors = ButtonDefaults.buttonColors(containerColor = Slate700),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("IRCTC Tickets", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate200)
                            }
                        }
                    }
                }
                2 -> {
                    // Private Bus Info & Surge Fare Radar
                    val priv = corridor.privateBus
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "Operators: ${priv.majorOperators.joinToString(", ")}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate200
                        )

                        // Fare Comparison Radar Box
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Slate900,
                            border = BorderStroke(1.dp, Slate700),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Regular Weekday Fare:", fontSize = 11.sp, color = Slate400)
                                    Text(priv.nonPeakFareRange, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoGreen)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Surge Weekend / Festival:", fontSize = 11.sp, color = SurgeWarningOrange)
                                    Text(priv.surgeWeekendFareRange, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SurgeWarningOrange)
                                }
                            }
                        }

                        Text(text = "⚠️ Boarding Warning: ${priv.boardingPointsAdvisory}", fontSize = 10.sp, color = SurgeWarningOrange, lineHeight = 14.sp)
                        Text(text = "💡 Tip: ${priv.travelerTip}", fontSize = 10.sp, color = Slate400, lineHeight = 14.sp)

                        Button(
                            onClick = { onOpenUrl(priv.bookingUrl) },
                            colors = ButtonDefaults.buttonColors(containerColor = SurgeWarningOrange),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.OpenInBrowser, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Check Live Seats & Fares on RedBus", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate900)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TransportComparisonCard(
    item: TransportModeComparison,
    onOpenBooking: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Operator Tag & Price
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(item.badgeColorHex).copy(alpha = 0.2f),
                    border = BorderStroke(1.dp, Color(item.badgeColorHex))
                ) {
                    Text(
                        text = item.operatorType,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(item.badgeColorHex),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                Text(
                    text = item.approxPriceRange,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoYellow
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = item.title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Slate200
            )

            Text(
                text = item.headline,
                fontSize = 12.sp,
                color = MonsoonBlue,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.howItHelpsTravelers,
                fontSize = 12.sp,
                color = Slate400,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Pros & Cons
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = Slate900,
                border = BorderStroke(1.dp, Slate700)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = "✅ Advantages (Why choose this):", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoGreen)
                    item.pros.forEach { pro ->
                        Text(text = "• $pro", fontSize = 11.sp, color = Slate200, modifier = Modifier.padding(vertical = 1.dp))
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(text = "⚠️ Watch Out (Limitations):", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SurgeWarningOrange)
                    item.cons.forEach { con ->
                        Text(text = "• $con", fontSize = 11.sp, color = Slate400, modifier = Modifier.padding(vertical = 1.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Best suited for
            Text(
                text = "🎯 Best Suited For: " + item.bestSuitedFor,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = AutoYellow
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Action Button: Book Tickets
            Button(
                onClick = onOpenBooking,
                colors = ButtonDefaults.buttonColors(containerColor = Color(item.badgeColorHex)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.OpenInBrowser,
                    contentDescription = null,
                    tint = Slate900,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Book on Official Portal (${item.bookingUrl.replace("https://", "").replace("www.", "").substringBefore("/")})",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
            }
        }
    }
}

@Composable
fun TicketPortalCard(
    item: TransportModeComparison,
    onOpenUrl: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate200
                )
                Text(
                    text = item.whereToBuyTickets,
                    fontSize = 11.sp,
                    color = Slate400,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = onOpenUrl,
                colors = ButtonDefaults.buttonColors(containerColor = AutoYellow),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Open Portal",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
            }
        }
    }
}

@Composable
fun SeasonGuideCard(season: SeasonTravelInfo) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Color(season.tagColorHex).copy(alpha = 0.6f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = season.seasonTitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(season.tagColorHex)
                )
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Slate900,
                    border = BorderStroke(1.dp, Slate700)
                ) {
                    Text(
                        text = season.monthRange,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate200,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = season.climateSummary,
                fontSize = 12.sp,
                color = Slate200,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "🌟 Top Destinations to Experience:",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = AutoYellow
            )
            season.topDestinationsToVisit.forEach { dest ->
                Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                    Text(text = "•", color = AutoYellow, fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = dest, fontSize = 11.sp, color = Slate200)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = Slate900,
                border = BorderStroke(1.dp, Slate700)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "⚠️ What to Avoid: " + season.whatToAvoid,
                        fontSize = 10.sp,
                        color = SurgeWarningOrange
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "🎒 Packing & Travel Tip: " + season.packingAndTravelTip,
                        fontSize = 10.sp,
                        color = AutoGreen
                    )
                }
            }
        }
    }
}

@Composable
fun GuideGuidelineCard(guide: OfficialGuideGuideline) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = guide.circuitOrSpot,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Slate200
            )

            Text(
                text = "Guide Type: " + guide.guideType,
                fontSize = 12.sp,
                color = AutoYellow,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = Slate900,
                border = BorderStroke(1.dp, Slate700)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Official Rate Card:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoGreen)
                        Text(text = guide.officialRateCard, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate200)
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(text = "🔍 How to Verify:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MonsoonBlue)
                    Text(text = guide.howToVerifyOfficialBadge, fontSize = 11.sp, color = Slate200)

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(text = "📍 How to Book / Hire:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoYellow)
                    Text(text = guide.howToBookOrHire, fontSize = 11.sp, color = Slate200)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "🚨 Scam Warning: " + guide.scamWarning,
                fontSize = 11.sp,
                color = SurgeWarningOrange
            )
        }
    }
}
