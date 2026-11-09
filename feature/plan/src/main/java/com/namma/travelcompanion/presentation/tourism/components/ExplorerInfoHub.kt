package com.namma.travelcompanion.presentation.tourism.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
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
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.SurgeWarningOrange

/** Three toggle tiles: city auto rates, dialects & food, scam buster. */
@Composable
internal fun InfoHubToggles(
    showTransport: Boolean,
    showDialects: Boolean,
    showScamBuster: Boolean,
    onToggleTransport: () -> Unit,
    onToggleDialects: () -> Unit,
    onToggleScamBuster: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Statewide Auto Tariffs Button
        Surface(
            modifier = Modifier
                .weight(1f)
                .clickable { onToggleTransport() },
            shape = RoundedCornerShape(12.dp),
            color = if (showTransport) MonsoonBlue.copy(alpha = 0.25f) else Slate800,
            border = BorderStroke(1.dp, if (showTransport) MonsoonBlue else Slate700)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DirectionsBus,
                        contentDescription = null,
                        tint = MonsoonBlue,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "City Auto Rates",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate200
                    )
                }
                Text(
                    text = "Mangalore, Mysuru, Hubli",
                    fontSize = 10.sp,
                    color = Slate400
                )
            }
        }

        // Regional Dialects & Food Button
        Surface(
            modifier = Modifier
                .weight(1f)
                .clickable { onToggleDialects() },
            shape = RoundedCornerShape(12.dp),
            color = if (showDialects) AutoGreen.copy(alpha = 0.25f) else Slate800,
            border = BorderStroke(1.dp, if (showDialects) AutoGreen else Slate700)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Restaurant,
                        contentDescription = null,
                        tint = AutoGreen,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Dialects & Food",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate200
                    )
                }
                Text(
                    text = "Tulu, Malnad, Uttara KA",
                    fontSize = 10.sp,
                    color = Slate400
                )
            }
        }

        // Scam Buster Button
        Surface(
            modifier = Modifier
                .weight(1f)
                .clickable { onToggleScamBuster() },
            shape = RoundedCornerShape(12.dp),
            color = if (showScamBuster) SurgeWarningOrange.copy(alpha = 0.25f) else Slate800,
            border = BorderStroke(1.dp, if (showScamBuster) SurgeWarningOrange else Slate700)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = SurgeWarningOrange,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Scam Buster",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate200
                    )
                }
                Text(
                    text = "MRP & Rules",
                    fontSize = 10.sp,
                    color = Slate400
                )
            }
        }
    }
}
