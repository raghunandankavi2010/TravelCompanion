package com.namma.travelcompanion.feature.commute.presentation.lens.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.feature.commute.domain.model.BusRouteInfo
import com.namma.travelcompanion.feature.commute.domain.model.ConductorPhrase
import com.namma.travelcompanion.core.ui.components.GlassCard

@Composable
internal fun BusRouteCard(
    route: BusRouteInfo,
    isSpeaking: Boolean,
    onPlayAnnouncement: () -> Unit,
    onSpeakPhrase: (ConductorPhrase) -> Unit,
    onDismiss: () -> Unit
) {
    var showConductorPhrases by remember { mutableStateOf(false) }

    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        cornerRadius = 20.dp,
        borderColor = Color(route.badgeColorHex)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header Row: Route Number & Dismiss
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(route.badgeColorHex)
                    ) {
                        Text(
                            text = route.routeNumber,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black,
                            color = Slate900,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = route.serviceCategory,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Slate400
                    )
                }

                IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Dismiss",
                        tint = Slate400,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Origin -> Destination in English & Kannada
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = route.originEn,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Slate200
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "➔", fontSize = 14.sp, color = AutoYellow)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = route.destinationEn,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoYellow
                    )
                }

                Text(
                    text = "${route.originKn} ➔ ${route.destinationKn}",
                    fontSize = 12.sp,
                    color = Slate400,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // VIA STOPS Ribbon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "VIA:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = AutoYellow)
                route.viaStopsEn.forEach { stop ->
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = Slate800,
                        border = BorderStroke(1.dp, Slate700)
                    ) {
                        Text(
                            text = stop,
                            fontSize = 10.sp,
                            color = Slate200,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Fare & Frequency Meta
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Fare: ${route.approxFareRange}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoGreen
                )
                Text(
                    text = route.frequencySummary,
                    fontSize = 10.sp,
                    color = Slate400
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Action Buttons: Audio Announcement & Conductor Talk
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onPlayAnnouncement,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSpeaking) AutoGreen else AutoYellow,
                        contentColor = Slate900
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = if (isSpeaking) Icons.Default.Stop else Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isSpeaking) "Speaking..." else "Announce",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                OutlinedButton(
                    onClick = { showConductorPhrases = !showConductorPhrases },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, MonsoonBlue),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MonsoonBlue)
                ) {
                    Icon(
                        imageVector = Icons.Default.RecordVoiceOver,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (showConductorPhrases) "Hide Helper" else "Talk Conductor",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Expandable Conductor Phrases
            AnimatedVisibility(visible = showConductorPhrases) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    HorizontalDivider(color = Slate700)
                    Text(
                        text = "QUICK CONDUCTOR AUDIO PHRASES (TAP TO SPEAK):",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoYellow,
                        fontFamily = FontFamily.Monospace
                    )

                    route.conductorSurvivalPhrases.forEach { phrase ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Slate800,
                            border = BorderStroke(1.dp, Slate700),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSpeakPhrase(phrase) }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = phrase.englishMeaning,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Slate200
                                    )
                                    Text(
                                        text = phrase.kannadaLipi,
                                        fontSize = 12.sp,
                                        color = AutoYellow
                                    )
                                    Text(
                                        text = "\"${phrase.phoneticKannada}\"",
                                        fontSize = 10.sp,
                                        color = Slate400
                                    )
                                }
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                    contentDescription = "Speak",
                                    tint = AutoYellow,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
