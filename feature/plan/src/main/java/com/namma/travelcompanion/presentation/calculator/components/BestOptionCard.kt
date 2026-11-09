package com.namma.travelcompanion.presentation.calculator.components

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
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FairMeterGreen
import com.namma.travelcompanion.core.theme.GoldPro
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.RideAppOption
import com.namma.travelcompanion.core.ui.components.BangaloreBadge

/** Gold hero card for the best estimated ride-app option. */
@Composable
internal fun BestOptionCard(
    best: RideAppOption,
    onOpenApp: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Slate800,
        border = BorderStroke(2.dp, GoldPro)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = GoldPro,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "BEST ESTIMATED OPTION",
                        color = GoldPro,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.5.sp
                    )
                }
                BangaloreBadge(
                    text = "${best.acceptancePercentage}% ACCEPTANCE",
                    badgeColor = FairMeterGreen,
                    textColor = Slate900
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = best.appName,
                        color = Slate200,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = "Estimated ETA: ~${best.etaMinutes} mins pickup",
                        color = Slate400,
                        fontSize = 12.sp
                    )
                }
                Text(
                    text = "₹${best.fareRupees.toInt()}",
                    color = AutoYellow,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "💡 ${best.proCommuteInsight}",
                color = Slate200,
                fontSize = 12.sp,
                lineHeight = 17.sp
            )

            if (best.recommendedTipRupees > 0) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "⚡ Pro tip: Add +₹${best.recommendedTipRupees} tip to get picked up in under 60 seconds during this traffic.",
                    color = AutoYellow,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { onOpenApp() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = AutoYellow),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.OpenInNew, contentDescription = null, tint = Slate900)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Open & Book on ${best.appName}", color = Slate900, fontWeight = FontWeight.Black)
                }
            }
        }
    }
}
