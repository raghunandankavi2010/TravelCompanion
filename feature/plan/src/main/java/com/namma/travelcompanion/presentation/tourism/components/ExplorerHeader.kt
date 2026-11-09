package com.namma.travelcompanion.presentation.tourism.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
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
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800

/** Title row with the INR/USD currency toggle pill. */
@Composable
internal fun ExplorerHeader(
    isForeignerMode: Boolean,
    onToggleForeignerMode: () -> Unit
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
                    text = "Travel Companion",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate200
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "🧭", fontSize = 22.sp)
            }
            Text(
                text = "Karnataka Explorer • Temples, Wildlife & Nature",
                fontSize = 12.sp,
                color = AutoYellow
            )
        }

        // Foreigner USD Currency Toggle Pill
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = if (isForeignerMode) AutoYellow.copy(alpha = 0.2f) else Slate800,
            border = BorderStroke(1.dp, if (isForeignerMode) AutoYellow else Slate700),
            modifier = Modifier.clickable { onToggleForeignerMode() }
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CurrencyExchange,
                    contentDescription = "Currency",
                    tint = if (isForeignerMode) AutoYellow else Slate400,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = if (isForeignerMode) "USD ($)" else "INR (₹)",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isForeignerMode) AutoYellow else Slate400
                )
            }
        }
    }
}

/** Quick-action card that opens the Bus Board & Signboard Lens. */
@Composable
internal fun BusLensCard(onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Slate800,
        border = BorderStroke(1.dp, AutoYellow.copy(alpha = 0.6f)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = AutoYellow.copy(alpha = 0.2f),
                    modifier = Modifier.size(42.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "🚌", fontSize = 20.sp)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Bus Board & Signboard Lens",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate200
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = AutoGreen.copy(alpha = 0.25f)
                        ) {
                            Text(
                                text = "ON-DEVICE AI",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = AutoGreen,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                            )
                        }
                    }
                    Text(
                        text = "Scan Kannada bus boards & temple signboards",
                        fontSize = 11.sp,
                        color = Slate400
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.CameraAlt,
                contentDescription = "Open Lens",
                tint = AutoYellow,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
