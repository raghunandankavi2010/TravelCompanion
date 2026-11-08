package com.namma.travelcompanion.feature.meter.ui.calculator

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Thunderstorm
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FairMeterGreen
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.GoldPro
import com.namma.travelcompanion.core.theme.MetroPurple
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.feature.meter.domain.model.AcceptanceChance
import com.namma.travelcompanion.feature.meter.domain.model.RideAppOption
import com.namma.travelcompanion.presentation.components.BangaloreBadge
import com.namma.travelcompanion.presentation.components.GlassCard

@Composable
fun FareEstimatorScreen(
    viewModel: FareEstimatorViewModel,
    onOpenKannadaNegotiator: () -> Unit
) {
    val context = LocalContext.current
    val fromHub by viewModel.fromHub.collectAsState()
    val toHub by viewModel.toHub.collectAsState()
    val traffic by viewModel.trafficMultiplier.collectAsState()
    val isRaining by viewModel.isRaining.collectAsState()
    val report by viewModel.radarReport.collectAsState()
    val isFetchingLive by viewModel.isFetchingLiveApi.collectAsState()
    val isLiveVerified by viewModel.isLiveApiVerified.collectAsState()

    var showFromMenu by remember { mutableStateOf(false) }
    var showToMenu by remember { mutableStateOf(false) }

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
                    text = "Bengaluru Ride Radar",
                    color = Slate200,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "Multi-App Price & Acceptance Odds AI",
                    color = Slate400,
                    fontSize = 12.sp
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                BangaloreBadge(
                    text = if (isLiveVerified) "🟢 LIVE API SYNC" else "⚡ REAL-TIME RADAR",
                    badgeColor = if (isLiveVerified) FairMeterGreen else AutoYellow,
                    textColor = Slate900
                )
                Spacer(modifier = Modifier.width(6.dp))
                IconButton(
                    onClick = { viewModel.syncWithLiveRideApis() },
                    modifier = Modifier.size(32.dp)
                ) {
                    if (isFetchingLive) {
                        CircularProgressIndicator(color = AutoYellow, strokeWidth = 2.dp, modifier = Modifier.size(16.dp))
                    } else {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh", tint = Slate400)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Pickup / Drop Pickers Card
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
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null, tint = AutoYellow)
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

                Spacer(modifier = Modifier.height(10.dp))

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
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null, tint = AutoYellow)
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

        Spacer(modifier = Modifier.height(12.dp))

        // Traffic & Weather Condition Modifiers
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
                label = { Text("ORR Peak", fontSize = 11.sp) },
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
                    contentDescription = null,
                    tint = if (isRaining) MonsoonBlue else Slate400
                )
            },
            label = {
                Text(
                    text = if (isRaining) "Bengaluru Rain Surge (+₹25–₹40 on apps)" else "Weather: Clear Skies",
                    fontSize = 11.sp
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MonsoonBlue.copy(alpha = 0.2f),
                selectedLabelColor = MonsoonBlue
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(14.dp))

        // HERO CARD: AI BEST RECOMMENDATION
        val best = report.bestAppRecommendation
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = Slate800,
            border = BorderStroke(2.dp, GoldPro)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = GoldPro,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "AI RECOMMENDED APP",
                            color = GoldPro,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 0.5.sp
                        )
                    }
                    BangaloreBadge(
                        text = "${best.acceptancePercentage}% ACCEPTANCE",
                        badgeColor = FairMeterGreen,
                        textColor = Slate900
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        Text(
                            text = best.appName,
                            color = Slate200,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = "Estimated ETA: ~${best.etaMinutes} mins pickup",
                            color = Slate400,
                            fontSize = 12.sp
                        )
                    }
                    Text(
                        text = "₹${best.fareRupees.toInt()}",
                        color = AutoYellow,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "💡 ${best.proCommuteInsight}",
                    color = Slate200,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )

                if (best.recommendedTipRupees > 0) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "⚡ Pro tip: Add +₹${best.recommendedTipRupees} tip to get picked up in under 60 seconds during this traffic.",
                        color = AutoYellow,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { launchRideApp(context, best) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = AutoYellow),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.OpenInNew, contentDescription = null, tint = Slate900)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Open & Book on ${best.appName}", color = Slate900, fontWeight = FontWeight.Black)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // MULTI-APP REAL-TIME COMPARISON LIST
        Text(
            text = "MULTI-APP ACCEPTANCE & FARE MATRIX",
            color = Slate400,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        report.options.forEach { option ->
            AppOptionRow(
                option = option,
                onLaunch = { launchRideApp(context, option) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))

        // TRANSPARENCY & API ARCHITECTURE NOTE
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            color = Slate800.copy(alpha = 0.6f),
            border = BorderStroke(1.dp, Slate700)
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = MonsoonBlue, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Live Sync Architecture: Integrates official Uber Rides API + ONDC Open Mobility Gateway for Namma Yatri + Bengaluru reverse-engineered surge algorithms.",
                    color = Slate400,
                    fontSize = 11.sp,
                    lineHeight = 15.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // STREET AUTO FAIR CASH ALTERNATIVE
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = Slate800,
            border = BorderStroke(1.dp, AutoGreen.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Direct Street Auto Alternative",
                            color = AutoGreen,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Flag down any passing auto & quote directly in Kannada",
                            color = Slate400,
                            fontSize = 11.sp
                        )
                    }
                    Text(
                        text = "₹${report.streetAutoAlternativeFare.toInt()}",
                        color = AutoGreen,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = onOpenKannadaNegotiator,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = AutoGreen),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Navigation, contentDescription = null, tint = Slate900)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Quote ₹${report.streetAutoAlternativeFare.toInt()} Cash in Kannada 🔊",
                            color = Slate900,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun AppOptionRow(
    option: RideAppOption,
    onLaunch: () -> Unit
) {
    val badgeColor = when (option.acceptanceChance) {
        AcceptanceChance.VERY_HIGH -> FairMeterGreen
        AcceptanceChance.HIGH -> AutoGreen
        AcceptanceChance.MODERATE -> AutoYellow
        AcceptanceChance.LOW_REJECTION_RISK -> FastMeterAlert
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Slate800,
        border = BorderStroke(1.dp, if (option.isBestChoiceOverall) GoldPro else Slate700)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = option.appName,
                        color = Slate200,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    BangaloreBadge(
                        text = "${option.acceptancePercentage}% Acceptance",
                        badgeColor = badgeColor,
                        textColor = if (option.acceptanceChance == AcceptanceChance.LOW_REJECTION_RISK) Slate200 else Slate900
                    )
                }

                Text(
                    text = "₹${option.fareRupees.toInt()}",
                    color = AutoYellow,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = option.proCommuteInsight,
                color = Slate400,
                fontSize = 11.sp,
                lineHeight = 15.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ETA: ~${option.etaMinutes} mins",
                    color = Slate400,
                    fontSize = 11.sp
                )

                OutlinedButton(
                    onClick = onLaunch,
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, AutoYellow)
                ) {
                    Text("Open App", fontSize = 11.sp, color = AutoYellow, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

private fun launchRideApp(context: Context, option: RideAppOption) {
    val pm = context.packageManager
    val launchIntent = pm.getLaunchIntentForPackage(option.packageName)
    if (launchIntent != null) {
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(launchIntent)
    } else {
        val storeIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${option.packageName}")).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            context.startActivity(storeIntent)
        } catch (e: Exception) {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=${option.packageName}")).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(webIntent)
        }
    }
}
