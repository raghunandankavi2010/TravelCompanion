package com.nammaauto.ai.presentation.safety

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nammaauto.ai.core.theme.AutoGreen
import com.nammaauto.ai.core.theme.AutoYellow
import com.nammaauto.ai.core.theme.FastMeterAlert
import com.nammaauto.ai.core.theme.MetroPurple
import com.nammaauto.ai.core.theme.Slate200
import com.nammaauto.ai.core.theme.Slate400
import com.nammaauto.ai.core.theme.Slate700
import com.nammaauto.ai.core.theme.Slate800
import com.nammaauto.ai.core.theme.Slate900
import com.nammaauto.ai.presentation.components.BangaloreBadge
import com.nammaauto.ai.presentation.components.FareMetricCard
import com.nammaauto.ai.presentation.components.GlassCard

@Composable
fun NightGuardianScreen(
    viewModel: NightGuardianViewModel
) {
    val context = LocalContext.current
    val corridor by viewModel.corridorState.collectAsState()
    val trip by viewModel.tripState.collectAsState()
    val destination by viewModel.destination.collectAsState()

    var showDestMenu by remember { mutableStateOf(false) }

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
                    text = "Night Guardian Radar",
                    color = Slate200,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "Bengaluru Late Night Commute & Route Safety",
                    color = Slate400,
                    fontSize = 12.sp
                )
            }
            BangaloreBadge(
                text = if (corridor.isMonitoringActive) "RADAR ACTIVE" else "STANDBY",
                badgeColor = if (corridor.isMonitoringActive) AutoGreen else Slate700,
                textColor = if (corridor.isMonitoringActive) Slate900 else AutoYellow
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Main Corridor Status Card
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            modifier = Modifier.size(36.dp),
                            shape = CircleShape,
                            color = if (corridor.isMonitoringActive) AutoGreen.copy(alpha = 0.2f) else Slate700
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Security,
                                    contentDescription = null,
                                    tint = if (corridor.isMonitoringActive) AutoGreen else Slate400,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Drop Destination:",
                                color = Slate400,
                                fontSize = 11.sp
                            )
                            Text(
                                text = destination.name,
                                color = Slate200,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Box {
                        OutlinedButton(
                            onClick = { showDestMenu = true },
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, Slate700)
                        ) {
                            Text("Change", fontSize = 11.sp, color = AutoYellow)
                        }
                        DropdownMenu(
                            expanded = showDestMenu,
                            onDismissRequest = { showDestMenu = false },
                            modifier = Modifier.background(Slate800)
                        ) {
                            viewModel.hubs.forEach { hub ->
                                DropdownMenuItem(
                                    text = { Text(hub.name, color = Slate200) },
                                    onClick = {
                                        viewModel.setDestination(hub)
                                        showDestMenu = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = corridor.safetyStatusMessage,
                    color = if (corridor.isOffRouteAlert) FastMeterAlert else Slate200,
                    fontSize = 12.sp,
                    fontWeight = if (corridor.isOffRouteAlert) FontWeight.Bold else FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = { viewModel.toggleMonitoring() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (corridor.isMonitoringActive) FastMeterAlert else AutoGreen
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = if (corridor.isMonitoringActive) "Stop Safety Radar" else "Activate Night Corridor Radar",
                        color = if (corridor.isMonitoringActive) Slate200 else Slate900,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Off-Route Detour Alert Banner
        AnimatedVisibility(
            visible = corridor.isOffRouteAlert,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = FastMeterAlert.copy(alpha = 0.15f),
                border = BorderStroke(1.dp, FastMeterAlert)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Detour Alert",
                        tint = FastMeterAlert,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "UNUSUAL DETOUR DETECTED (+${corridor.currentDeviationMeters.toInt()}m)",
                            color = FastMeterAlert,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = "Vehicle has deviated significantly from primary commute corridor into bypass road.",
                            color = Slate200,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Telemetry Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FareMetricCard(
                title = "Speed",
                amount = "${trip.currentSpeedKmh.toInt()} km/h",
                subtitle = "Normal commute velocity",
                accentColor = AutoYellow,
                modifier = Modifier.weight(1f)
            )
            FareMetricCard(
                title = "Corridor Drift",
                amount = "${corridor.currentDeviationMeters.toInt()} m",
                subtitle = "Threshold: < 400m",
                accentColor = if (corridor.isOffRouteAlert) FastMeterAlert else AutoGreen,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Emergency SOS Broadcast Section
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = Slate800,
            border = BorderStroke(1.dp, Slate700)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "EMERGENCY BROADCAST & BTP HELPLINE",
                    color = FastMeterAlert,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.5.sp
                )
                Text(
                    text = "One-tap emergency broadcast sends live GPS link to family & dials police:",
                    color = Slate400,
                    fontSize = 11.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = { viewModel.broadcastSosMessage(context) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = FastMeterAlert),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Share, contentDescription = null, tint = Slate200)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Share Live GPS", color = Slate200, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                        }
                    }

                    Button(
                        onClick = { viewModel.dialBangalorePoliceHelpline(context) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = MetroPurple),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Call, contentDescription = null, tint = Slate200)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("BTP 9480801000", color = Slate200, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                OutlinedButton(
                    onClick = { viewModel.triggerSimulatedDetour() },
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, Slate700)
                ) {
                    Text("Simulate Night Detour Alert (Testing)", fontSize = 11.sp, color = Slate400)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
