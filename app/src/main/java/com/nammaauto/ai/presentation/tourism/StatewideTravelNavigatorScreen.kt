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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.OpenInBrowser
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
import com.nammaauto.ai.domain.model.StatewideTransportData
import com.nammaauto.ai.domain.model.TransportModeComparison
import com.nammaauto.ai.presentation.components.GlassCard

enum class NavigatorTab(val label: String, val iconTag: String) {
    COMPARE_TRANSPORT("Govt vs Private Options", "🚆"),
    TICKET_PORTALS("Where to Buy Tickets", "🎟️"),
    BEST_SEASONS("Best Time to Travel", "🌤️"),
    GUIDES_INFO("Verified Guides & Audio", "👨‍🏫")
}

@Composable
fun StatewideTravelNavigatorScreen() {
    val context = LocalContext.current
    var selectedTab by remember { mutableStateOf(NavigatorTab.COMPARE_TRANSPORT) }

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
                        text = "Govt vs Private Fleets • Where to Buy • Best Seasons • Guides",
                        fontSize = 12.sp,
                        color = AutoYellow
                    )
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
                    text = "Book on " + item.whereToBuyTickets.substringBefore("."),
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
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
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
                    color = MonsoonBlue
                )
                Text(
                    text = "Fare range: " + item.approxPriceRange,
                    fontSize = 11.sp,
                    color = AutoYellow
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
                        color = MonsoonBlue
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
