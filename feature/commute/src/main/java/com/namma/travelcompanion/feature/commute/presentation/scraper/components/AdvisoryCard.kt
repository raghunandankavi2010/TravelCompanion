package com.namma.travelcompanion.feature.commute.presentation.scraper.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.SurgeWarningOrange
import com.namma.travelcompanion.network.api.LiveTravelAdvisory

/** "Gate & Permit Advisories" section title. */
@Composable
internal fun AdvisoryHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "🚨 Gate & Permit Advisories",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = FastMeterAlert
        )
        Text(
            text = "Curated rules, confirm locally",
            fontSize = 10.sp,
            color = Slate400
        )
    }
}

@Composable
internal fun AdvisoryCard(advisory: LiveTravelAdvisory) {
    val borderColor = when (advisory.urgencyLevel) {
        "CRITICAL" -> FastMeterAlert
        "CAUTION" -> SurgeWarningOrange
        else -> AutoYellow
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = advisory.destination,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate200
                )
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = borderColor.copy(alpha = 0.2f),
                    border = BorderStroke(1.dp, borderColor)
                ) {
                    Text(
                        text = advisory.urgencyLevel,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = borderColor,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = advisory.title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = borderColor
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = advisory.groundTruthAdvice,
                fontSize = 11.sp,
                color = Slate400,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Source: ${advisory.lastVerified}",
                fontSize = 10.sp,
                color = Slate400
            )
        }
    }
}
