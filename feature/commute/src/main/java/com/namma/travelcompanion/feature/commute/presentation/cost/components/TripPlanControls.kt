package com.namma.travelcompanion.feature.commute.presentation.cost.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.TripDestinationItem

/** Horizontally scrolling destination chips. */
@Composable
internal fun DestinationChips(
    destinations: List<TripDestinationItem>,
    selectedSpotIds: Set<String>,
    onToggle: (String) -> Unit
) {
    Text(
        text = "🗺️ Select Destinations Included in Trip:",
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = Slate200
    )
    Spacer(modifier = Modifier.height(6.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        destinations.forEach { spot ->
            val isSelected = selectedSpotIds.contains(spot.id)
            FilterChip(
                selected = isSelected,
                onClick = { onToggle(spot.id) },
                label = { Text(spot.name, fontSize = 11.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = AutoYellow,
                    selectedLabelColor = Slate900,
                    containerColor = Slate800,
                    labelColor = Slate400
                ),
                border = BorderStroke(1.dp, if (isSelected) AutoYellow else Slate700)
            )
        }
    }
}

/** Trip duration and traveller count steppers. */
@Composable
internal fun TripStepperCard(
    tripDays: Int,
    adultsCount: Int,
    childrenCount: Int,
    onTripDaysChange: (Int) -> Unit,
    onAdultsChange: (Int) -> Unit,
    onChildrenChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            StepperRow(
                label = "Trip Duration (Days)",
                value = "$tripDays Days",
                onMinus = { onTripDaysChange(tripDays - 1) },
                onPlus = { onTripDaysChange(tripDays + 1) }
            )

            StepperRow(
                label = "Adult Travellers",
                value = "$adultsCount Adults",
                onMinus = { onAdultsChange(adultsCount - 1) },
                onPlus = { onAdultsChange(adultsCount + 1) }
            )

            StepperRow(
                label = "Children (< 12 yrs)",
                value = "$childrenCount Children",
                onMinus = { onChildrenChange(childrenCount - 1) },
                onPlus = { onChildrenChange(childrenCount + 1) }
            )
        }
    }
}

@Composable
private fun StepperRow(
    label: String,
    value: String,
    onMinus: () -> Unit,
    onPlus: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 12.sp, color = Slate200)

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onMinus, modifier = Modifier.size(28.dp)) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Slate900,
                    border = BorderStroke(1.dp, Slate700),
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Remove, null, tint = AutoYellow, modifier = Modifier.size(14.dp))
                    }
                }
            }

            Text(
                text = value,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = AutoYellow,
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            IconButton(onClick = onPlus, modifier = Modifier.size(28.dp)) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Slate900,
                    border = BorderStroke(1.dp, Slate700),
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Add, null, tint = AutoYellow, modifier = Modifier.size(14.dp))
                    }
                }
            }
        }
    }
}
