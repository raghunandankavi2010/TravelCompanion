package com.namma.travelcompanion.feature.commute.presentation.cost.components

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
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900

/** Money-saving tips card. */
@Composable
internal fun MoneySavingTipsCard(tips: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, AutoGreen.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Savings, null, tint = AutoGreen, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "💡 Money-Saving Insider Tips:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoGreen
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            tips.forEach { tip ->
                Text(
                    text = "• $tip",
                    fontSize = 11.sp,
                    color = Slate200,
                    lineHeight = 16.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}

/** KSRTC and ASI booking shortcuts. */
@Composable
internal fun BookingButtons(
    onBookBus: () -> Unit,
    onBookMonuments: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = { onBookBus() },
            colors = ButtonDefaults.buttonColors(containerColor = MonsoonBlue),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text("Book KSRTC Bus", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Slate900)
        }

        OutlinedButton(
            onClick = { onBookMonuments() },
            border = BorderStroke(1.dp, AutoYellow),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text("Book ASI Monuments", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = AutoYellow)
        }
    }
}
