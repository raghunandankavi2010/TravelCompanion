package com.namma.travelcompanion.presentation.tourism.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Train
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoGreenDark
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.MetroPurple
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.KarnatakaSpot

/** One destination row in the Explorer list. */
@Composable
fun DestinationCard(
    spot: KarnatakaSpot,
    isForeignerMode: Boolean,
    isSpeaking: Boolean,
    onPlayAudio: () -> Unit,
    onStopAudio: () -> Unit,
    onViewDetails: () -> Unit,
    onOpenBookingUrl: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onViewDetails() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row: Category Badge + Region Badge + District
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = getCategoryColor(spot.category).copy(alpha = 0.2f),
                        border = BorderStroke(1.dp, getCategoryColor(spot.category))
                    ) {
                        Text(
                            text = spot.category.label,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = getCategoryColor(spot.category),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Slate700.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = spot.region.iconTag + " " + spot.region.label,
                            fontSize = 10.sp,
                            color = Slate200,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }
                }

                Text(
                    text = spot.district,
                    fontSize = 11.sp,
                    color = Slate400,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Spot Title & Kannada Lipi
            Text(
                text = spot.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Slate200
            )
            Text(
                text = spot.kannadaName,
                fontSize = 13.sp,
                color = AutoYellow,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = spot.tagline,
                fontSize = 12.sp,
                color = Slate400,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Transit Hub Indicator
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Slate900.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Train,
                    contentDescription = null,
                    tint = MonsoonBlue,
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = spot.nearestTransitHub,
                    fontSize = 11.sp,
                    color = MonsoonBlue,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Dual Pricing Pill / Box
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = Slate900.copy(alpha = 0.9f),
                border = BorderStroke(1.dp, Slate700)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isForeignerMode) "🌍 Foreign Tourist Tariff:" else "🇮🇳 Domestic Entry:",
                            fontSize = 10.sp,
                            color = Slate400,
                            fontWeight = FontWeight.SemiBold
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (isForeignerMode) {
                                    if (spot.foreignerPricing.foreignerFeeInr == 0) "Free Entry"
                                    else "${spot.foreignerPricing.approxUsdEquivalent} (₹${spot.foreignerPricing.foreignerFeeInr})"
                                } else {
                                    if (spot.foreignerPricing.domesticFeeInr == 0) "Free Entry"
                                    else "₹${spot.foreignerPricing.domesticFeeInr}"
                                },
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (spot.foreignerPricing.foreignerFeeInr == 0) AutoGreen else AutoYellow
                            )
                        }
                    }

                    // Portal Badge
                    val isAranya = spot.bookingInfo.badgeTag.contains("ARANYA")
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (isAranya) AutoGreenDark.copy(alpha = 0.3f) else MetroPurple.copy(alpha = 0.2f),
                        border = BorderStroke(
                            1.dp,
                            if (isAranya) AutoGreen else MetroPurple
                        )
                    ) {
                        Text(
                            text = spot.bookingInfo.badgeTag,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isAranya) AutoGreen else MetroPurple,
                            maxLines = 1,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Quick Audio Phrase Box
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = Slate800,
                border = BorderStroke(1.dp, Slate700.copy(alpha = 0.6f))
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "🗣️ Local Kannada to Ask:",
                            fontSize = 10.sp,
                            color = AutoYellow,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "“${spot.survivalKannadaPhrase}”",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Slate200,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = spot.survivalKannadaMeaning,
                            fontSize = 11.sp,
                            color = Slate400,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    IconButton(
                        onClick = {
                            if (isSpeaking) onStopAudio() else onPlayAudio()
                        },
                        modifier = Modifier
                            .size(36.dp)
                            .background(if (isSpeaking) FastMeterAlert else AutoYellow, CircleShape)
                    ) {
                        Icon(
                            imageVector = if (isSpeaking) Icons.Default.Stop else Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = "Play Speech",
                            tint = Slate900,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action Buttons: Official Booking + Full Details
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val isAranya = spot.bookingInfo.badgeTag.contains("ARANYA")
                Button(
                    onClick = { onOpenBookingUrl() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isAranya) AutoGreenDark else Slate700
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.OpenInBrowser,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Slate200
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isAranya) "Aranya Booking" else "Official Portal",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate200
                    )
                }

                OutlinedButton(
                    onClick = { onViewDetails() },
                    border = BorderStroke(1.dp, AutoYellow),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = AutoYellow
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Details & Rules",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoYellow
                    )
                }
            }
        }
    }
}
