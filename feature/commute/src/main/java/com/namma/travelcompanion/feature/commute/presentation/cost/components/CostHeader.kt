package com.namma.travelcompanion.feature.commute.presentation.cost.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.CurrencyExchange
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
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800

/** Title row with the INR/USD toggle. */
@Composable
internal fun CostHeader(
    isForeigner: Boolean,
    onToggleForeigner: () -> Unit
) {
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Trip Cost Estimator",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate200
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "💰", fontSize = 22.sp)
            }
            Text(
                text = "Itemized Real-Time Travel Budget & Tariff Engine",
                fontSize = 12.sp,
                color = AutoYellow
            )
        }

        // Currency / Foreigner toggle
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = if (isForeigner) AutoYellow.copy(alpha = 0.2f) else Slate800,
            border = BorderStroke(1.dp, if (isForeigner) AutoYellow else Slate700),
            modifier = Modifier.clickable { onToggleForeigner() }
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.CurrencyExchange, null, tint = AutoYellow, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = if (isForeigner) "USD ($)" else "INR (₹)",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoYellow
                )
            }
        }
    }
}
