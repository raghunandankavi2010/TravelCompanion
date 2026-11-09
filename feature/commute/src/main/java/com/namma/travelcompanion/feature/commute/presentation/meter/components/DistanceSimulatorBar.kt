package com.namma.travelcompanion.feature.commute.presentation.meter.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800

/** Debug-only quick distance simulator. Callers must keep the BuildConfig.DEBUG guard. */
@Composable
internal fun DistanceSimulatorBar(onSimulate: (Double) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Slate800,
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "QUICK DISTANCE SIMULATOR",
                color = AutoYellow,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = { onSimulate(0.5) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("+0.5 km", fontSize = 11.sp, color = Slate200)
                }
                OutlinedButton(
                    onClick = { onSimulate(1.5) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("+1.5 km", fontSize = 11.sp, color = AutoYellow)
                }
                OutlinedButton(
                    onClick = { onSimulate(5.0) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("+5.0 km", fontSize = 11.sp, color = MonsoonBlue)
                }
            }
        }
    }
}
