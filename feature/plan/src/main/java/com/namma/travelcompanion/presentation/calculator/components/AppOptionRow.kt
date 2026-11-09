package com.namma.travelcompanion.presentation.calculator.components

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
import com.namma.travelcompanion.core.theme.FairMeterGreen
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.GoldPro
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.AcceptanceChance
import com.namma.travelcompanion.domain.model.RideAppOption
import com.namma.travelcompanion.core.ui.components.BangaloreBadge

/** One row of the multi-app fare matrix. */
@Composable
internal fun AppOptionRow(
    option: RideAppOption,
    onLaunch: () -> Unit
) {
    val badgeColor = when (option.acceptanceChance) {
        AcceptanceChance.VERY_HIGH -> FairMeterGreen
        AcceptanceChance.HIGH -> AutoGreen
        AcceptanceChance.MODERATE -> AutoYellow
        AcceptanceChance.LOW_REJECTION_RISK -> FastMeterAlert
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Slate800,
        border = BorderStroke(1.dp, if (option.isBestChoiceOverall) GoldPro else Slate700)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = option.appName,
                        color = Slate200,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    BangaloreBadge(
                        text = "${option.acceptancePercentage}% Acceptance",
                        badgeColor = badgeColor,
                        textColor = if (option.acceptanceChance == AcceptanceChance.LOW_REJECTION_RISK) Slate200 else Slate900
                    )
                }

                Text(
                    text = "₹${option.fareRupees.toInt()}",
                    color = AutoYellow,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = option.proCommuteInsight,
                color = Slate400,
                fontSize = 11.sp,
                lineHeight = 15.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ETA: ~${option.etaMinutes} mins",
                    color = Slate400,
                    fontSize = 11.sp
                )

                OutlinedButton(
                    onClick = onLaunch,
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, AutoYellow)
                ) {
                    Text("Open App", fontSize = 11.sp, color = AutoYellow, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
