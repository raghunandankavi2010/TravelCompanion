package com.namma.travelcompanion.presentation.calculator.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.domain.model.BangaloreHub
import com.namma.travelcompanion.core.ui.components.GlassCard

/** Pickup / drop hub dropdowns. */
@Composable
internal fun HubPickerCard(
    hubs: List<BangaloreHub>,
    fromHub: BangaloreHub,
    toHub: BangaloreHub,
    onFromSelected: (BangaloreHub) -> Unit,
    onToSelected: (BangaloreHub) -> Unit
) {
    var showFromMenu by remember { mutableStateOf(false) }
    var showToMenu by remember { mutableStateOf(false) }

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
                    hubs.forEach { hub ->
                        DropdownMenuItem(
                            text = { Text("${hub.name} (${hub.zone})", color = Slate200) },
                            onClick = {
                                onFromSelected(hub)
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
                    hubs.forEach { hub ->
                        DropdownMenuItem(
                            text = { Text("${hub.name} (${hub.zone})", color = Slate200) },
                            onClick = {
                                onToSelected(hub)
                                showToMenu = false
                            }
                        )
                    }
                }
            }
        }
    }
}
