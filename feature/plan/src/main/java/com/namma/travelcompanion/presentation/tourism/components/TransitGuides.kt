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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.theme.SurgeWarningOrange
import com.namma.travelcompanion.domain.model.OfficialGuideGuideline
import com.namma.travelcompanion.domain.model.SeasonTravelInfo
import com.namma.travelcompanion.core.ui.components.GlassCard

@Composable
internal fun GuideVerificationCard() {
        GlassCard(
            backgroundColor = Slate800.copy(alpha = 0.95f),
            borderColor = AutoGreen.copy(alpha = 0.5f)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Verified, contentDescription = null, tint = AutoGreen, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Official Ministry of Tourism Guide Verification",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoYellow
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Government-approved tourist guides MUST carry an official laminated photo identity card on a green or blue lanyard with hologram issued by the Archaeological Survey of India or Karnataka State Tourism Development Corporation. Reject unauthorized touts claiming entrance is closed or offering 'private view rooms'.",
                    fontSize = 11.sp,
                    color = Slate200,
                    lineHeight = 16.sp
                )
            }
        }
}

@Composable
internal fun SeasonGuideCard(season: SeasonTravelInfo) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Color(season.tagColorHex).copy(alpha = 0.6f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = season.seasonTitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(season.tagColorHex)
                )
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Slate900,
                    border = BorderStroke(1.dp, Slate700)
                ) {
                    Text(
                        text = season.monthRange,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate200,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = season.climateSummary,
                fontSize = 12.sp,
                color = Slate200,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "🌟 Top Destinations to Experience:",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = AutoYellow
            )
            season.topDestinationsToVisit.forEach { dest ->
                Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                    Text(text = "•", color = AutoYellow, fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = dest, fontSize = 11.sp, color = Slate200)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = Slate900,
                border = BorderStroke(1.dp, Slate700)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "⚠️ What to Avoid: " + season.whatToAvoid,
                        fontSize = 10.sp,
                        color = SurgeWarningOrange
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "🎒 Packing & Travel Tip: " + season.packingAndTravelTip,
                        fontSize = 10.sp,
                        color = AutoGreen
                    )
                }
            }
        }
    }
}

@Composable
internal fun GuideGuidelineCard(guide: OfficialGuideGuideline) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = guide.circuitOrSpot,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Slate200
            )

            Text(
                text = "Guide Type: " + guide.guideType,
                fontSize = 12.sp,
                color = AutoYellow,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = Slate900,
                border = BorderStroke(1.dp, Slate700)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Official Rate Card:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoGreen)
                        Text(text = guide.officialRateCard, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate200)
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(text = "🔍 How to Verify:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MonsoonBlue)
                    Text(text = guide.howToVerifyOfficialBadge, fontSize = 11.sp, color = Slate200)

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(text = "📍 How to Book / Hire:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoYellow)
                    Text(text = guide.howToBookOrHire, fontSize = 11.sp, color = Slate200)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "🚨 Scam Warning: " + guide.scamWarning,
                fontSize = 11.sp,
                color = SurgeWarningOrange
            )
        }
    }
}
