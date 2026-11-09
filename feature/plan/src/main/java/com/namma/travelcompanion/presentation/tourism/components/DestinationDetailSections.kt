package com.namma.travelcompanion.presentation.tourism.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.theme.SurgeWarningOrange
import com.namma.travelcompanion.domain.model.KarnatakaSpot

/** Scrollable sections of the destination detail dialog. */
internal fun LazyListScope.destinationDetailItems(
    spot: KarnatakaSpot,
    isSpeaking: Boolean,
    onPlayAudio: () -> Unit,
    onStopAudio: () -> Unit
) {
    item {
        Text(
            text = spot.name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Slate200
        )
        Text(
            text = spot.kannadaName,
            fontSize = 15.sp,
            color = AutoYellow,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "District: ${spot.district} | ${spot.nearestTransitHub}",
            fontSize = 11.sp,
            color = MonsoonBlue
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = spot.overview,
            fontSize = 13.sp,
            color = Slate400,
            lineHeight = 18.sp
        )
    }

    // Key Highlights
    item {
        DetailSectionCard(title = "✨ Top Highlights") {
            spot.keyHighlights.forEach { highlight ->
                Row(
                    modifier = Modifier.padding(vertical = 3.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(text = "•", color = AutoYellow, fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = highlight,
                        fontSize = 12.sp,
                        color = Slate200
                    )
                }
            }
        }
    }

    // Pricing & Foreigner Guidance
    item {
        DetailSectionCard(title = "💰 Official Ticket & Foreigner Pricing") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Domestic Citizen:",
                        fontSize = 11.sp,
                        color = Slate400
                    )
                    Text(
                        text = if (spot.foreignerPricing.domesticFeeInr == 0) "Free" else "₹${spot.foreignerPricing.domesticFeeInr}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate200
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Foreign National:",
                        fontSize = 11.sp,
                        color = Slate400
                    )
                    Text(
                        text = if (spot.foreignerPricing.foreignerFeeInr == 0) "Free" else "${spot.foreignerPricing.approxUsdEquivalent} (₹${spot.foreignerPricing.foreignerFeeInr})",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoYellow
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Includes: ${spot.foreignerPricing.feeIncludes}",
                fontSize = 11.sp,
                color = MonsoonBlue
            )
        }
    }

    // Timings & Best Season
    item {
        DetailSectionCard(title = "🕒 Best Season & Operating Timings") {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Best Season: ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoYellow
                )
                Text(
                    text = spot.bestSeason,
                    fontSize = 12.sp,
                    color = Slate200
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Timings: ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoYellow
                )
                Text(
                    text = spot.timings,
                    fontSize = 12.sp,
                    color = Slate200
                )
            }
        }
    }

    // Dress Code & Cultural Etiquette
    item {
        DetailSectionCard(title = "👔 Dress Code & Cultural Etiquette") {
            Text(
                text = spot.foreignerPricing.dressCodeRules,
                fontSize = 12.sp,
                color = Slate200
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Photography: ${spot.foreignerPricing.photographyPolicy}",
                fontSize = 11.sp,
                color = Slate400
            )
        }
    }

    // Scam Alert & Tourist Safeguard
    item {
        DetailSectionCard(
            title = "⚠️ Scam Alert & Precautions",
            borderColor = FastMeterAlert.copy(alpha = 0.5f)
        ) {
            Text(
                text = spot.foreignerPricing.scamAlertNotes,
                fontSize = 12.sp,
                color = SurgeWarningOrange
            )
        }
    }

    // How to Reach & Transit
    item {
        DetailSectionCard(title = "🚌 How to Reach & Regional Transit") {
            Text(
                text = spot.howToReachTransit,
                fontSize = 12.sp,
                color = Slate200
            )
        }
    }

    // Survival Audio Phrase
    item {
        DetailSectionCard(
            title = "🗣️ On-Location Kannada Audio Phrase",
            borderColor = AutoYellow.copy(alpha = 0.5f)
        ) {
            Text(
                text = spot.survivalKannadaLipi,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = AutoYellow
            )
            Text(
                text = spot.survivalKannadaPhrase,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Slate200
            )
            Text(
                text = "Meaning: ${spot.survivalKannadaMeaning}",
                fontSize = 11.sp,
                color = Slate400
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (isSpeaking) onStopAudio() else onPlayAudio()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSpeaking) FastMeterAlert else AutoYellow
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = if (isSpeaking) Icons.Default.Stop else Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = null,
                    tint = Slate900,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (isSpeaking) "Stop Audio" else "Speak Kannada to Driver / Counter",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
            }
        }
    }
}

@Composable
internal fun DetailSectionCard(
    title: String,
    borderColor: Color = Slate700,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Slate800.copy(alpha = 0.7f),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = AutoYellow
            )
            Spacer(modifier = Modifier.height(6.dp))
            content()
        }
    }
}
