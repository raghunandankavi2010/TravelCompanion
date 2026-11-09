package com.namma.travelcompanion.presentation.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Thunderstorm
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate900

/** Traffic level chips and the rain toggle. */
@Composable
internal fun ConditionChips(
    traffic: Double,
    isRaining: Boolean,
    onTrafficSelected: (Double) -> Unit,
    onToggleRain: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = traffic == 1.0,
            onClick = { onTrafficSelected(1.0) },
            label = { Text("Normal", fontSize = 11.sp) },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = AutoGreen,
                selectedLabelColor = Slate900
            ),
            modifier = Modifier.weight(1f)
        )
        FilterChip(
            selected = traffic == 1.25,
            onClick = { onTrafficSelected(1.25) },
            label = { Text("ORR Peak", fontSize = 11.sp) },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = AutoYellow,
                selectedLabelColor = Slate900
            ),
            modifier = Modifier.weight(1f)
        )
        FilterChip(
            selected = traffic == 1.5,
            onClick = { onTrafficSelected(1.5) },
            label = { Text("Silkboard Jam", fontSize = 11.sp) },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = FastMeterAlert,
                selectedLabelColor = Slate200
            ),
            modifier = Modifier.weight(1.2f)
        )
    }

    Spacer(modifier = Modifier.height(6.dp))

    FilterChip(
        selected = isRaining,
        onClick = { onToggleRain() },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Thunderstorm,
                contentDescription = null,
                tint = if (isRaining) MonsoonBlue else Slate400
            )
        },
        label = {
            Text(
                text = if (isRaining) "Bengaluru Rain Surge (+₹25–₹40 on apps)" else "Weather: Clear Skies",
                fontSize = 11.sp
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MonsoonBlue.copy(alpha = 0.2f),
            selectedLabelColor = MonsoonBlue
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
