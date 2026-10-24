package com.nammaauto.ai.presentation.calculator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Thunderstorm
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nammaauto.ai.core.theme.AutoGreen
import com.nammaauto.ai.core.theme.AutoYellow
import com.nammaauto.ai.core.theme.FairMeterGreen
import com.nammaauto.ai.core.theme.FastMeterAlert
import com.nammaauto.ai.core.theme.MetroPurple
import com.nammaauto.ai.core.theme.MonsoonBlue
import com.nammaauto.ai.core.theme.Slate200
import com.nammaauto.ai.core.theme.Slate400
import com.nammaauto.ai.core.theme.Slate700
import com.nammaauto.ai.core.theme.Slate800
import com.nammaauto.ai.core.theme.Slate900
import com.nammaauto.ai.core.util.BangaloreHub
import com.nammaauto.ai.presentation.components.BangaloreBadge
import com.nammaauto.ai.presentation.components.FareMetricCard
import com.nammaauto.ai.presentation.components.GlassCard

@Composable
fun FareEstimatorScreen(
    viewModel: FareEstimatorViewModel,
    onOpenKannadaNegotiator: () -> Unit
) {
    val fromHub by viewModel.fromHub.collectAsState()
    val toHub by viewModel.toHub.collectAsState()
    val traffic by viewModel.trafficMultiplier.collectAsState()
    val isRaining by viewModel.isRaining.collectAsState()
    val comparison by viewModel.comparison.collectAsState()

    var showFromMenu by remember { mutableStateOf(false) }
    var showToMenu by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Fair-Fare AI Arbiter",
                    color = Slate200,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "Lawful Meter vs Fair Haggling vs Cab Surge",
                    color = Slate400,
                    fontSize = 12.sp
                )
            }
            BangaloreBadge(
                text = "BTP RATE 2026",
                badgeColor = AutoYellow,
                textColor = Slate900
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Origin Selector
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(text = "FROM (PICKUP)", color = Slate400, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Box {
                    OutlinedButton(
                        onClick = { showFromMenu = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Slate700)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.Start) {
                                Text(text = fromHub.name, color = Slate200, fontWeight = FontWeight.Bold)
                                Text(text = fromHub.landmark, color = Slate400, fontSize = 11.sp)
                            }
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown", tint = AutoYellow)
                        }
                    }
                    DropdownMenu(
                        expanded = showFromMenu,
                        onDismissRequest = { showFromMenu = false },
                        modifier = Modifier.background(Slate800)
                    ) {
                        viewModel.hubs.forEach { hub ->
                            DropdownMenuItem(
                                text = { Text("${hub.name} (${hub.zone})", color = Slate200) },
                                onClick = {
                                    viewModel.setFromHub(hub)
                                    showFromMenu = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Destination Selector
                Text(text = "TO (DESTINATION)", color = Slate400, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Box {
                    OutlinedButton(
                        onClick = { showToMenu = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Slate700)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.Start) {
                                Text(text = toHub.name, color = Slate200, fontWeight = FontWeight.Bold)
                                Text(text = toHub.landmark, color = Slate400, fontSize = 11.sp)
                            }
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown", tint = AutoYellow)
                        }
                    }
                    DropdownMenu(
                        expanded = showToMenu,
                        onDismissRequest = { showToMenu = false },
                        modifier = Modifier.background(Slate800)
                    ) {
                        viewModel.hubs.forEach { hub ->
                            DropdownMenuItem(
                                text = { Text("${hub.name} (${hub.zone})", color = Slate200) },
                                onClick = {
                                    viewModel.setToHub(hub)
                                    showToMenu = false
                                }
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Traffic & Monsoon Condition Modifiers
        Text(text = "CURRENT BENGALURU CONDITIONS", color = Slate400, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = traffic == 1.0,
                onClick = { viewModel.setTrafficMultiplier(1.0) },
                label = { Text("Normal", fontSize = 11.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = AutoGreen,
                    selectedLabelColor = Slate900
                ),
                modifier = Modifier.weight(1f)
            )
            FilterChip(
                selected = traffic == 1.25,
                onClick = { viewModel.setTrafficMultiplier(1.25) },
                label = { Text("ORR Heavy", fontSize = 11.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = AutoYellow,
                    selectedLabelColor = Slate900
                ),
                modifier = Modifier.weight(1f)
            )
            FilterChip(
                selected = traffic == 1.5,
                onClick = { viewModel.setTrafficMultiplier(1.5) },
                label = { Text("Silkboard Jam", fontSize = 11.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = FastMeterAlert,
                    selectedLabelColor = Slate200
                ),
                modifier = Modifier.weight(1.2f)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        FilterChip(
            selected = isRaining,
            onClick = { viewModel.toggleRain() },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Thunderstorm,
                    contentDescription = "Monsoon",
                    tint = if (isRaining) MonsoonBlue else Slate400
                )
            },
            label = {
                Text(
                    text = if (isRaining) "Bengaluru Monsoon Rain (+₹30 fair buffer active)" else "Weather: Clear Skies",
                    fontSize = 11.sp
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MonsoonBlue.copy(alpha = 0.2f),
                selectedLabelColor = MonsoonBlue
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // The Tri-Fare Comparison Matrix Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = Slate800,
            border = BorderStroke(1.dp, AutoYellow.copy(alpha = 0.4f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "ESTIMATED DISTANCE: ${comparison.distanceKm} KM",
                        color = AutoYellow,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = if (comparison.isNight) "10PM-5AM Night Rules" else "Daytime Tariff",
                        color = Slate400,
                        fontSize = 11.sp
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FareMetricCard(
                        title = "1. BTP Lawful Fare",
                        amount = "₹${comparison.legalGovtFare.toInt()}",
                        subtitle = "Exact meter reading",
                        accentColor = FairMeterGreen,
                        modifier = Modifier.weight(1f)
                    )
                    FareMetricCard(
                        title = "2. Win-Win Offer",
                        amount = "₹${comparison.fairNegotiationQuote.toInt()}",
                        subtitle = "Prevents auto refusal",
                        accentColor = AutoYellow,
                        modifier = Modifier.weight(1f)
                    )
                    FareMetricCard(
                        title = "3. Cab Surge",
                        amount = "₹${comparison.estimatedCabSurgePrice.toInt()}",
                        subtitle = "Uber / Ola estimate",
                        accentColor = FastMeterAlert,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Potential Savings Callout
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    color = FairMeterGreen.copy(alpha = 0.15f),
                    border = BorderStroke(1.dp, FairMeterGreen)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AttachMoney,
                            contentDescription = "Savings",
                            tint = FairMeterGreen
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "You save ~₹${comparison.potentialSavings.toInt()} by taking an auto with this fair offer over surging cabs!",
                            color = Slate200,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Direct Action to Kannada Negotiation Assistant
        Button(
            onClick = onOpenKannadaNegotiator,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MetroPurple),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Navigation, contentDescription = null, tint = Slate200)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Quote ₹${comparison.fairNegotiationQuote.toInt()} in Fluent Kannada",
                    color = Slate200,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
