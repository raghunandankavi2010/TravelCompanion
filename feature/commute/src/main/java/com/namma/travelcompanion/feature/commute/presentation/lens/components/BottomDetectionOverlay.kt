package com.namma.travelcompanion.feature.commute.presentation.lens.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.feature.commute.BuildConfig
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.feature.commute.domain.model.BusRouteInfo
import com.namma.travelcompanion.feature.commute.domain.model.BusSignLensScanResult
import com.namma.travelcompanion.feature.commute.domain.model.ConductorPhrase
import com.namma.travelcompanion.feature.commute.domain.model.DetectedSignboardInfo
import com.namma.travelcompanion.core.ui.components.GlassCard

import androidx.compose.foundation.layout.navigationBarsPadding

@Composable
internal fun BottomDetectionOverlay(
    scanResult: com.namma.travelcompanion.feature.commute.domain.model.BusSignLensScanResult,
    isSpeaking: Boolean,
    onPlayRouteAnnouncement: (BusRouteInfo) -> Unit,
    onPlaySignboardAnnouncement: (DetectedSignboardInfo) -> Unit,
    onSpeakPhrase: (ConductorPhrase) -> Unit,
    onSimulateRoute: (String) -> Unit,
    onSimulateSignboard: (String) -> Unit,
    onClearDetection: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(14.dp)
        ) {
            val detectedRoute = scanResult.detectedRoute
            val detectedSign = scanResult.detectedSignboard

            AnimatedVisibility(
                visible = detectedRoute != null,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                detectedRoute?.let { route ->
                    BusRouteCard(
                        route = route,
                        isSpeaking = isSpeaking,
                        onPlayAnnouncement = { onPlayRouteAnnouncement(route) },
                        onSpeakPhrase = onSpeakPhrase,
                        onDismiss = onClearDetection
                    )
                }
            }

            AnimatedVisibility(
                visible = detectedSign != null && detectedRoute == null,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                detectedSign?.let { sign ->
                    SignboardDetailCard(
                        sign = sign,
                        isSpeaking = isSpeaking,
                        onPlayAnnouncement = { onPlaySignboardAnnouncement(sign) },
                        onDismiss = onClearDetection
                    )
                }
            }

            // If Idle (Nothing detected yet), show simulation pills and tips
            if (detectedRoute == null && detectedSign == null) {
                GlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    cornerRadius = 18.dp
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(16.dp),
                                    strokeWidth = 2.dp,
                                    color = AutoYellow
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Live On-Device Vision Scanner Active",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Slate200
                                )
                            }
                            Text(text = "0-Latency", fontSize = 10.sp, color = AutoGreen)
                        }

                        Text(
                            text = "Detects BMTC/KSRTC route numbers & Kannada signs (footwear, darshan, milestones).",
                            fontSize = 11.sp,
                            color = Slate400,
                            modifier = Modifier.padding(top = 4.dp, bottom = 10.dp)
                        )

                        if (BuildConfig.DEBUG) {
                            Text(
                                text = "TEST SIMULATOR (TAP TO PREVIEW BOARDS):",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = AutoYellow,
                                fontFamily = FontFamily.Monospace
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                FilterChip(
                                    selected = false,
                                    onClick = { onSimulateRoute("KIA-8") },
                                    label = { Text("✈️ KIA-8 (Airport)", fontSize = 11.sp) },
                                    colors = FilterChipDefaults.filterChipColors(labelColor = Slate200)
                                )
                                FilterChip(
                                    selected = false,
                                    onClick = { onSimulateRoute("335-E") },
                                    label = { Text("🏢 335-E (ITPL)", fontSize = 11.sp) },
                                    colors = FilterChipDefaults.filterChipColors(labelColor = Slate200)
                                )
                                FilterChip(
                                    selected = false,
                                    onClick = { onSimulateRoute("KSRTC MYS-EXP") },
                                    label = { Text("🏰 KSRTC (Mysuru)", fontSize = 11.sp) },
                                    colors = FilterChipDefaults.filterChipColors(labelColor = Slate200)
                                )
                                FilterChip(
                                    selected = false,
                                    onClick = { onSimulateSignboard("sign_padarakshe") },
                                    label = { Text("🥿 Footwear Counter", fontSize = 11.sp) },
                                    colors = FilterChipDefaults.filterChipColors(labelColor = Slate200)
                                )
                                FilterChip(
                                    selected = false,
                                    onClick = { onSimulateSignboard("sign_sheegra_darshana") },
                                    label = { Text("⚡ Special Darshan", fontSize = 11.sp) },
                                    colors = FilterChipDefaults.filterChipColors(labelColor = Slate200)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
