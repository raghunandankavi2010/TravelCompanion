package com.namma.travelcompanion.feature.commute.presentation.cost.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.AccommodationType
import com.namma.travelcompanion.domain.model.DiningBudgetPreference
import com.namma.travelcompanion.domain.model.TravelTransportMode

/** Transport mode chips. */
@Composable
internal fun TransportModeSelector(
    selected: TravelTransportMode,
    onSelected: (TravelTransportMode) -> Unit
) {
    Text(
        text = "🚗 Travel Transport Mode:",
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = Slate200
    )
    Spacer(modifier = Modifier.height(6.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TravelTransportMode.values().forEach { mode ->
            val isSelected = selected == mode
            FilterChip(
                selected = isSelected,
                onClick = { onSelected(mode) },
                label = { Text("${mode.iconTag} ${mode.label}", fontSize = 11.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MonsoonBlue,
                    selectedLabelColor = Slate900,
                    containerColor = Slate800,
                    labelColor = Slate400
                ),
                border = BorderStroke(1.dp, if (isSelected) MonsoonBlue else Slate700)
            )
        }
    }
}

/** Accommodation tier chips. */
@Composable
internal fun StayTierSelector(
    selected: AccommodationType,
    onSelected: (AccommodationType) -> Unit
) {
    Text(
        text = "🏨 Accommodation Tier:",
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = Slate200
    )
    Spacer(modifier = Modifier.height(6.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AccommodationType.values().forEach { stay ->
            val isSelected = selected == stay
            FilterChip(
                selected = isSelected,
                onClick = { onSelected(stay) },
                label = { Text("${stay.iconTag} ${stay.label}", fontSize = 11.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = AutoGreen,
                    selectedLabelColor = Slate900,
                    containerColor = Slate800,
                    labelColor = Slate400
                ),
                border = BorderStroke(1.dp, if (isSelected) AutoGreen else Slate700)
            )
        }
    }
}

/** Pure-veg dining budget options. */
@Composable
internal fun DiningBudgetSelector(
    selected: DiningBudgetPreference,
    onSelected: (DiningBudgetPreference) -> Unit
) {
    Text(
        text = "🍲 Pure Veg Dining Budget:",
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = Slate200
    )
    Spacer(modifier = Modifier.height(6.dp))
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        DiningBudgetPreference.values().forEach { dining ->
            val isSelected = selected == dining
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = if (isSelected) AutoGreen.copy(alpha = 0.2f) else Slate800,
                border = BorderStroke(1.dp, if (isSelected) AutoGreen else Slate700),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelected(dining) }
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = dining.label,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) AutoGreen else Slate200
                    )
                    if (isSelected) {
                        Text("✓ Selected", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoGreen)
                    }
                }
            }
        }
    }
}
