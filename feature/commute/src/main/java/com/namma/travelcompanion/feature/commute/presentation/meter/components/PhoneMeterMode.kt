package com.namma.travelcompanion.feature.commute.presentation.meter.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FairMeterGreen
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.data.location.LiveTripState
import com.namma.travelcompanion.domain.model.AppMarketBenchmark
import com.namma.travelcompanion.core.ui.components.BangaloreBadge
import com.namma.travelcompanion.core.ui.components.FareMetricCard
import com.namma.travelcompanion.core.ui.components.GlassCard

/** High-contrast digital HUD meter driven by the phone's GPS trip. */
@Composable
internal fun PhoneMeterHud(
    trip: LiveTripState,
    appBenchmark: AppMarketBenchmark,
    onToggleTrip: () -> Unit,
    onResetTrip: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp)),
        color = Color(0xFF0A0F1D),
        border = BorderStroke(2.dp, AutoYellow.copy(alpha = 0.8f))
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "BENGALURU VIRTUAL METER",
                    color = AutoYellow,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp
                )
                BangaloreBadge(
                    text = if (trip.isRunning) "LIVE GPS ACTIVE" else "METER READY",
                    badgeColor = if (trip.isRunning) AutoGreen else Slate700,
                    textColor = if (trip.isRunning) Slate900 else AutoYellow
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Digital Glow Numerals
            Text(
                text = "₹${appBenchmark.winWinDirectOffer.toInt()}",
                color = AutoYellow,
                fontSize = 58.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.Monospace,
                letterSpacing = (-2).sp
            )

            Text(
                text = "MARKET FAIR-FARE (MATCHES RIDE-APP PAYOUT)",
                color = FairMeterGreen,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Telemetry Row inside Meter
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("DISTANCE", color = Slate400, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Text("${trip.distanceKm} km", color = Slate200, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("SPEED", color = Slate400, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Text("${trip.currentSpeedKmh.toInt()} km/h", color = Slate200, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("WAITING", color = Slate400, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Text("${trip.waitingMinutes}m", color = Slate200, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("BTP GOVT BASE", color = Slate400, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Text("₹${trip.currentLegalFare.toInt()}", color = Slate400, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Start / Stop Trip Controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = {
                        onToggleTrip()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (trip.isRunning) FastMeterAlert else AutoGreen
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (trip.isRunning) Icons.Default.Stop else Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = if (trip.isRunning) Slate200 else Slate900
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (trip.isRunning) "Stop Ride Meter" else "Start Phone Meter",
                            color = if (trip.isRunning) Slate200 else Slate900,
                            fontWeight = FontWeight.Black
                        )
                    }
                }

                IconButton(
                    onClick = { onResetTrip() },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Slate800, RoundedCornerShape(12.dp))
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "Reset", tint = Slate400)
                }
            }
        }
    }
}

/** Ride-app benchmark card: the fair cash offer to quote when the driver has no meter. */
@Composable
internal fun AppBenchmarkCard(
    appBenchmark: AppMarketBenchmark,
    onPlayKannadaOffer: () -> Unit
) {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        borderColor = AutoYellow.copy(alpha = 0.5f)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "RIDE-APP BENCHMARK INTELLIGENCE",
                    color = AutoYellow,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.5.sp
                )
                BangaloreBadge(
                    text = "NO-METER WIN-WIN",
                    badgeColor = FairMeterGreen,
                    textColor = Slate900
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Why do drivers refuse meters? Because ride apps pay ~₹55–₹65 for short rides (1.5 km). Quote this fair app rate directly:",
                color = Slate200,
                fontSize = 12.sp,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Side-by-side App breakdown
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FareMetricCard(
                    title = "Namma Yatri",
                    amount = "₹${appBenchmark.nammaYatriEstimate.toInt()}",
                    subtitle = "Short ride floor",
                    accentColor = MonsoonBlue,
                    modifier = Modifier.weight(1f)
                )
                FareMetricCard(
                    title = "Uber Auto",
                    amount = "₹${appBenchmark.uberAutoEstimate.toInt()}",
                    subtitle = "Platform fee incl.",
                    accentColor = AutoYellow,
                    modifier = Modifier.weight(1f)
                )
                FareMetricCard(
                    title = "Fair Cash Offer",
                    amount = "₹${appBenchmark.winWinDirectOffer.toInt()}",
                    subtitle = "Driver accepts instantly",
                    accentColor = FairMeterGreen,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // One-Tap Kannada Audio Quote for No-Meter Ride
            Button(
                onClick = { onPlayKannadaOffer() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = AutoYellow),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.VolumeUp, contentDescription = null, tint = Slate900)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Offer ₹${appBenchmark.winWinDirectOffer.toInt()} in Kannada (Audio) 🔊",
                        color = Slate900,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "🗣️ “${appBenchmark.recommendedPhonetic}”",
                color = Slate400,
                fontSize = 11.sp
            )
        }
    }
}
