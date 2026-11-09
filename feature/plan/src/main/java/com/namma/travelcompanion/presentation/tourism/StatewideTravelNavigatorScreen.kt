package com.namma.travelcompanion.presentation.tourism


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.StatewideTransportData
import com.namma.travelcompanion.core.ui.components.openUrl
import com.namma.travelcompanion.presentation.tourism.components.BusLensActionCard
import com.namma.travelcompanion.presentation.tourism.components.CorridorFilterChips
import com.namma.travelcompanion.presentation.tourism.components.CorridorTransitCard
import com.namma.travelcompanion.presentation.tourism.components.GuideGuidelineCard
import com.namma.travelcompanion.presentation.tourism.components.GuideVerificationCard
import com.namma.travelcompanion.presentation.tourism.components.LiveOfficialPortalsHub
import com.namma.travelcompanion.presentation.tourism.components.NavigatorHeader
import com.namma.travelcompanion.presentation.tourism.components.NavigatorTab
import com.namma.travelcompanion.presentation.tourism.components.NavigatorTabBar
import com.namma.travelcompanion.presentation.tourism.components.SeasonGuideCard
import com.namma.travelcompanion.presentation.tourism.components.TicketPortalCard
import com.namma.travelcompanion.presentation.tourism.components.TransportComparisonCard
import com.namma.travelcompanion.presentation.tourism.components.TransportVerdictCard

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
            item {
                NavigatorHeader()
            }

            item {
                BusLensActionCard(onClick = onNavigateToBusLens)
            }

            item {
                NavigatorTabBar(selectedTab = selectedTab, onSelect = { selectedTab = it })
            }

            // Content based on selected tab
            when (selectedTab) {
                NavigatorTab.LIVE_TRANSIT -> {
                    // Quick Official Portals Strip
                    item {
                        LiveOfficialPortalsHub(
                            onOpenUrl = { openUrl(context, it) },
                            onCall = { number ->
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
                                context.startActivity(intent)
                            }
                        )
                    }

                    item {
                        CorridorFilterChips(
                            selectedFilter = selectedCorridorFilter,
                            onSelect = { selectedCorridorFilter = it }
                        )
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
                            onOpenUrl = { openUrl(context, it) }
                        )
                    }
                }

                NavigatorTab.COMPARE_TRANSPORT -> {
                    item {
                        TransportVerdictCard()
                    }

                    items(StatewideTransportData.transportComparisons, key = { it.id }) { item ->
                        TransportComparisonCard(
                            item = item,
                            onOpenBooking = { openUrl(context, item.bookingUrl) }
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
                            onOpenUrl = { openUrl(context, item.bookingUrl) }
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
                        GuideVerificationCard()
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
