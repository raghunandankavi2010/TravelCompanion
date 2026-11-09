package com.namma.travelcompanion.presentation.tourism.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Train
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.namma.travelcompanion.core.ui.components.GlassCard

@Composable
internal fun LiveOfficialPortalsHub(
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
internal fun CorridorFilterChips(
    selectedFilter: String,
    onSelect: (String) -> Unit
) {
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
                    val isSelected = selectedFilter == filter
                    FilterChip(
                        selected = isSelected,
                        onClick = { onSelect(filter) },
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
