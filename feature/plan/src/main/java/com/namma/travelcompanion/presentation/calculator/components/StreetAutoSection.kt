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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Navigation
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
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900

/** Disclaimer that fares are estimates, not live quotes. */
@Composable
internal fun EstimateDisclaimer() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        color = Slate800.copy(alpha = 0.6f),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = MonsoonBlue, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "These are estimates from the official auto tariff and typical app pricing, not live quotes. Ride apps do not share prices with other apps; open the app for the exact fare.",
                color = Slate400,
                fontSize = 11.sp,
                lineHeight = 15.sp
            )
        }
    }
}

/** Street-auto cash fare alternative with a shortcut to the Kannada negotiator. */
@Composable
internal fun StreetAutoCard(
    fare: Double,
    onOpenKannadaNegotiator: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = Slate800,
        border = BorderStroke(1.dp, AutoGreen.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Direct Street Auto Alternative",
                        color = AutoGreen,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Flag down any passing auto & quote directly in Kannada",
                        color = Slate400,
                        fontSize = 11.sp
                    )
                }
                Text(
                    text = "₹${fare.toInt()}",
                    color = AutoGreen,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = onOpenKannadaNegotiator,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = AutoGreen),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Navigation, contentDescription = null, tint = Slate900)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Quote ₹${fare.toInt()} Cash in Kannada 🔊",
                        color = Slate900,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
    }
}
