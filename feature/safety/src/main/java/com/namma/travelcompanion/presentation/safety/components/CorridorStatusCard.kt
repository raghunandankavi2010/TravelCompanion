package com.namma.travelcompanion.presentation.safety.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.BangaloreHub
import com.namma.travelcompanion.domain.model.NightCorridorState
import com.namma.travelcompanion.core.ui.components.GlassCard

/** Corridor status card: destination picker, status message and radar toggle. */
@Composable
internal fun CorridorStatusCard(
    corridor: NightCorridorState,
    destination: BangaloreHub,
    hubs: List<BangaloreHub>,
    onDestinationSelected: (BangaloreHub) -> Unit,
    onToggleMonitoring: () -> Unit
) {
    var showDestMenu by remember { mutableStateOf(false) }

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
                        hubs.forEach { hub ->
                            DropdownMenuItem(
                                text = { Text(hub.name, color = Slate200) },
                                onClick = {
                                    onDestinationSelected(hub)
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
                onClick = onToggleMonitoring,
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
}
