package com.nammaauto.ai.presentation.cost

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nammaauto.ai.core.theme.AutoGreen
import com.nammaauto.ai.core.theme.AutoYellow
import com.nammaauto.ai.core.theme.GoldPro
import com.nammaauto.ai.core.theme.MonsoonBlue
import com.nammaauto.ai.core.theme.Slate200
import com.nammaauto.ai.core.theme.Slate400
import com.nammaauto.ai.core.theme.Slate700
import com.nammaauto.ai.core.theme.Slate800
import com.nammaauto.ai.core.theme.Slate900
import com.nammaauto.ai.domain.model.AccommodationType
import com.nammaauto.ai.domain.model.DiningBudgetPreference
import com.nammaauto.ai.domain.model.TravelTransportMode

@Composable
fun TravelCostCalculatorScreen(viewModel: TravelCostViewModel) {
    val context = LocalContext.current
    val selectedSpotIds by viewModel.selectedSpotIds.collectAsState()
    val tripDays by viewModel.tripDays.collectAsState()
    val adultsCount by viewModel.adultsCount.collectAsState()
    val childrenCount by viewModel.childrenCount.collectAsState()
    val isForeigner by viewModel.isForeigner.collectAsState()
    val transportMode by viewModel.transportMode.collectAsState()
    val stayType by viewModel.stayType.collectAsState()
    val diningPreference by viewModel.diningPreference.collectAsState()
    val breakdown by viewModel.breakdown.collectAsState()

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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Trip Cost Estimator",
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate200
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "💰", fontSize = 22.sp)
                        }
                        Text(
                            text = "Itemized Real-Time Travel Budget & Tariff Engine",
                            fontSize = 12.sp,
                            color = AutoYellow
                        )
                    }

                    // Currency / Foreigner toggle
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = if (isForeigner) AutoYellow.copy(alpha = 0.2f) else Slate800,
                        border = BorderStroke(1.dp, if (isForeigner) AutoYellow else Slate700),
                        modifier = Modifier.clickable { viewModel.toggleForeigner() }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.CurrencyExchange, null, tint = AutoYellow, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isForeigner) "USD ($)" else "INR (₹)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = AutoYellow
                            )
                        }
                    }
                }
            }

            // Big Total Budget Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Slate800),
                    border = BorderStroke(1.5.dp, GoldPro)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "ESTIMATED TOTAL BUDGET",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate400,
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = breakdown.approxUsdEquivalent,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = AutoYellow
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "₹${breakdown.totalEstimatedCostInr}",
                            fontSize = 34.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Slate900,
                                border = BorderStroke(1.dp, Slate700),
                                modifier = Modifier.weight(1f)
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text("Per Person", fontSize = 10.sp, color = Slate400)
                                    Text("₹${breakdown.perPersonCostInr}", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = AutoGreen)
                                }
                            }

                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Slate900,
                                border = BorderStroke(1.dp, Slate700),
                                modifier = Modifier.weight(1f)
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text("Per Day", fontSize = 10.sp, color = Slate400)
                                    Text("₹${breakdown.perDayCostInr}", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MonsoonBlue)
                                }
                            }

                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Slate900,
                                border = BorderStroke(1.dp, Slate700),
                                modifier = Modifier.weight(1f)
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text("Route Dist.", fontSize = 10.sp, color = Slate400)
                                    Text("~${breakdown.roundTripDistanceKm} km", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Slate200)
                                }
                            }
                        }

                        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Slate700)

                        // Itemized Breakdown
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            CostPill(icon = "🚌", label = "Transport", amount = "₹${breakdown.transportCostInr}")
                            CostPill(icon = "🏨", label = "Stay", amount = "₹${breakdown.stayCostInr}")
                            CostPill(icon = "🍲", label = "Veg Dining", amount = "₹${breakdown.diningCostInr}")
                            CostPill(icon = "🎟️", label = "Entry Fees", amount = "₹${breakdown.entryTicketsCostInr}")
                        }
                    }
                }
            }

            // Destinations Multi-Select
            item {
                Text(
                    text = "🗺️ Select Destinations Included in Trip:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate200
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    viewModel.availableDestinations.forEach { spot ->
                        val isSelected = selectedSpotIds.contains(spot.id)
                        FilterChip(
                            selected = isSelected,
                            onClick = { viewModel.toggleDestination(spot.id) },
                            label = { Text(spot.name, fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = AutoYellow,
                                selectedLabelColor = Slate900,
                                containerColor = Slate800,
                                labelColor = Slate400
                            ),
                            border = BorderStroke(1.dp, if (isSelected) AutoYellow else Slate700)
                        )
                    }
                }
            }

            // Days & Travellers Controls
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Slate800),
                    border = BorderStroke(1.dp, Slate700)
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        StepperRow(
                            label = "Trip Duration (Days)",
                            value = "$tripDays Days",
                            onMinus = { viewModel.setTripDays(tripDays - 1) },
                            onPlus = { viewModel.setTripDays(tripDays + 1) }
                        )

                        StepperRow(
                            label = "Adult Travellers",
                            value = "$adultsCount Adults",
                            onMinus = { viewModel.setAdults(adultsCount - 1) },
                            onPlus = { viewModel.setAdults(adultsCount + 1) }
                        )

                        StepperRow(
                            label = "Children (< 12 yrs)",
                            value = "$childrenCount Children",
                            onMinus = { viewModel.setChildren(childrenCount - 1) },
                            onPlus = { viewModel.setChildren(childrenCount + 1) }
                        )
                    }
                }
            }

            // Transport Mode Selection
            item {
                Text(
                    text = "🚗 Travel Transport Mode:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate200
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TravelTransportMode.values().forEach { mode ->
                        val isSelected = transportMode == mode
                        FilterChip(
                            selected = isSelected,
                            onClick = { viewModel.setTransportMode(mode) },
                            label = { Text("${mode.iconTag} ${mode.label}", fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MonsoonBlue,
                                selectedLabelColor = Slate900,
                                containerColor = Slate800,
                                labelColor = Slate400
                            ),
                            border = BorderStroke(1.dp, if (isSelected) MonsoonBlue else Slate700)
                        )
                    }
                }
            }

            // Stay & Dining Preferences
            item {
                Text(
                    text = "🏨 Accommodation Tier:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate200
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AccommodationType.values().forEach { stay ->
                        val isSelected = stayType == stay
                        FilterChip(
                            selected = isSelected,
                            onClick = { viewModel.setStayType(stay) },
                            label = { Text("${stay.iconTag} ${stay.label}", fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = AutoGreen,
                                selectedLabelColor = Slate900,
                                containerColor = Slate800,
                                labelColor = Slate400
                            ),
                            border = BorderStroke(1.dp, if (isSelected) AutoGreen else Slate700)
                        )
                    }
                }
            }

            item {
                Text(
                    text = "🍲 Pure Veg Dining Budget:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate200
                )
                Spacer(modifier = Modifier.height(6.dp))
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    DiningBudgetPreference.values().forEach { dining ->
                        val isSelected = diningPreference == dining
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isSelected) AutoGreen.copy(alpha = 0.2f) else Slate800,
                            border = BorderStroke(1.dp, if (isSelected) AutoGreen else Slate700),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.setDiningPreference(dining) }
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = dining.label,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) AutoGreen else Slate200
                                )
                                if (isSelected) {
                                    Text("✓ Selected", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoGreen)
                                }
                            }
                        }
                    }
                }
            }

            // Smart Money-Saving Tips
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Slate800),
                    border = BorderStroke(1.dp, AutoGreen.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Savings, null, tint = AutoGreen, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "💡 Money-Saving Insider Tips:",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = AutoGreen
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        breakdown.moneySavingTips.forEach { tip ->
                            Text(
                                text = "• $tip",
                                fontSize = 11.sp,
                                color = Slate200,
                                lineHeight = 16.sp,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }
                }
            }

            // Booking Launch Buttons
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { openBookingPortal(context, "https://ksrtc.in") },
                        colors = ButtonDefaults.buttonColors(containerColor = MonsoonBlue),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Book KSRTC Bus", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Slate900)
                    }

                    OutlinedButton(
                        onClick = { openBookingPortal(context, "https://asi.nic.in") },
                        border = BorderStroke(1.dp, AutoYellow),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Book ASI Monuments", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = AutoYellow)
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
fun CostPill(icon: String, label: String, amount: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = icon, fontSize = 16.sp)
        Text(text = label, fontSize = 9.sp, color = Slate400)
        Text(text = amount, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate200)
    }
}

@Composable
fun StepperRow(
    label: String,
    value: String,
    onMinus: () -> Unit,
    onPlus: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 12.sp, color = Slate200)

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onMinus, modifier = Modifier.size(28.dp)) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Slate900,
                    border = BorderStroke(1.dp, Slate700),
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Remove, null, tint = AutoYellow, modifier = Modifier.size(14.dp))
                    }
                }
            }

            Text(
                text = value,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = AutoYellow,
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            IconButton(onClick = onPlus, modifier = Modifier.size(28.dp)) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Slate900,
                    border = BorderStroke(1.dp, Slate700),
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Add, null, tint = AutoYellow, modifier = Modifier.size(14.dp))
                    }
                }
            }
        }
    }
}

fun openBookingPortal(context: Context, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    } catch (_: Exception) {}
}
