package com.namma.travelcompanion.presentation.safety.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.Warning
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
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.domain.model.NightCorridorState
import com.namma.travelcompanion.core.ui.components.FareMetricCard

/** Animated banner shown when the ride drifts off the expected route. */
@Composable
internal fun DetourAlertBanner(corridor: NightCorridorState) {
    AnimatedVisibility(
        visible = corridor.isOffRouteAlert,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = FastMeterAlert.copy(alpha = 0.15f),
            border = BorderStroke(1.dp, FastMeterAlert)
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Detour Alert",
                    tint = FastMeterAlert,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "UNUSUAL DETOUR DETECTED (+${corridor.currentDeviationMeters.toInt()}m)",
                        color = FastMeterAlert,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = "The ride is moving away from your drop point. Ask the driver, or share your location now.",
                        color = Slate200,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}

/** Speed and corridor drift tiles. */
@Composable
internal fun TelemetryGrid(
    speedKmh: Int,
    corridor: NightCorridorState,
    detourToleranceMeters: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        FareMetricCard(
            title = "Speed",
            amount = "${speedKmh} km/h",
            subtitle = "Normal commute velocity",
            accentColor = AutoYellow,
            modifier = Modifier.weight(1f)
        )
        FareMetricCard(
            title = "Corridor Drift",
            amount = "${corridor.currentDeviationMeters.toInt()} m",
            subtitle = "Alert at ${detourToleranceMeters} m",
            accentColor = if (corridor.isOffRouteAlert) FastMeterAlert else AutoGreen,
            modifier = Modifier.weight(1f)
        )
    }
}
