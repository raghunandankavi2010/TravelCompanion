package com.namma.travelcompanion.presentation.tourism.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Train
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.MetroPurple
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.theme.SurgeWarningOrange
import com.namma.travelcompanion.domain.model.StatewideCorridorTransit
import com.namma.travelcompanion.core.ui.components.GlassCard

@Composable
internal fun CorridorTransitCard(
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
                0 -> CorridorGovtBusPanel(bus = corridor.govtBus, onOpenUrl = onOpenUrl)
                1 -> CorridorRailPanel(rail = corridor.railway, onOpenUrl = onOpenUrl)
                2 -> CorridorPrivateBusPanel(priv = corridor.privateBus, onOpenUrl = onOpenUrl)
            }
        }
    }
}
