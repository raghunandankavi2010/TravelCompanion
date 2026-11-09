package com.namma.travelcompanion.presentation.tourism.components

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BeachAccess
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Hiking
import androidx.compose.material.icons.filled.Kayaking
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MetroPurple
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.theme.SurgeWarningOrange
import com.namma.travelcompanion.domain.model.KarnatakaRegion
import com.namma.travelcompanion.domain.model.TourismCategory

/** Horizontally scrolling region / circuit selector. */
@Composable
internal fun RegionSelector(
    selectedRegion: KarnatakaRegion,
    onSelectRegion: (KarnatakaRegion) -> Unit
) {
    val regionScrollState = rememberScrollState()
    Column {
        Text(
            text = "EXPLORE BY REGION / CIRCUIT:",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Slate400,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(regionScrollState),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            KarnatakaRegion.values().forEach { region ->
                val isSelected = selectedRegion == region
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) AutoYellow else Slate800,
                    border = BorderStroke(1.dp, if (isSelected) AutoYellow else Slate700),
                    modifier = Modifier.clickable { onSelectRegion(region) }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = region.iconTag, fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = region.label,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Slate900 else Slate200
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun ExplorerSearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = { onSearchQueryChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "Search Hampi, Jog Falls, Kudremukh, Netrani, Udupi...",
                color = Slate400,
                fontSize = 13.sp
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = AutoYellow
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { onSearchQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear",
                        tint = Slate400
                    )
                }
            }
        },
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Slate800,
            unfocusedContainerColor = Slate800,
            focusedBorderColor = AutoYellow,
            unfocusedBorderColor = Slate700,
            focusedTextColor = Slate200,
            unfocusedTextColor = Slate200
        ),
        singleLine = true
    )
}

@Composable
internal fun CategoryFilterRow(
    selectedCategory: TourismCategory?,
    onSelectCategory: (TourismCategory?) -> Unit
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // All Pill
        FilterChip(
            selected = selectedCategory == null,
            onClick = { onSelectCategory(null) },
            label = { Text("🌟 All Categories") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = AutoYellow,
                selectedLabelColor = Slate900,
                containerColor = Slate800,
                labelColor = Slate400
            ),
            border = BorderStroke(1.dp, if (selectedCategory == null) AutoYellow else Slate700)
        )

        // Categories
        TourismCategory.values().forEach { cat ->
            val isSelected = selectedCategory == cat
            val icon = getCategoryIcon(cat)
            FilterChip(
                selected = isSelected,
                onClick = { onSelectCategory(if (isSelected) null else cat) },
                leadingIcon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = if (isSelected) Slate900 else AutoYellow
                    )
                },
                label = { Text(cat.label) },
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

@Composable
internal fun ResultsBanner(
    resultCount: Int,
    selectedRegion: KarnatakaRegion
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Showing ${resultCount} destinations in ${selectedRegion.label}",
            fontSize = 12.sp,
            color = Slate400
        )
        Text(
            text = selectedRegion.districtsDescription,
            fontSize = 11.sp,
            color = AutoYellow,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
internal fun EmptyDestinations() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "🔍", fontSize = 40.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "No destinations found",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Slate200
            )
            Text(
                text = "Try switching region or searching another keyword",
                fontSize = 12.sp,
                color = Slate400
            )
        }
}

fun getCategoryIcon(cat: TourismCategory): ImageVector {
    return when (cat) {
        TourismCategory.TEMPLES_HERITAGE -> Icons.Default.TempleHindu
        TourismCategory.ADVENTURE_SPORTS -> Icons.Default.Kayaking
        TourismCategory.TREKKING_TRAILS -> Icons.Default.Hiking
        TourismCategory.BEACHES -> Icons.Default.BeachAccess
        TourismCategory.ISLANDS -> Icons.Default.Explore
        TourismCategory.WATERFALLS_NATURE -> Icons.Default.WaterDrop
        TourismCategory.WILDLIFE_SAFARI -> Icons.Default.Pets
    }
}

fun getCategoryColor(cat: TourismCategory): Color {
    return when (cat) {
        TourismCategory.TEMPLES_HERITAGE -> AutoYellow
        TourismCategory.ADVENTURE_SPORTS -> SurgeWarningOrange
        TourismCategory.TREKKING_TRAILS -> AutoGreen
        TourismCategory.BEACHES -> MonsoonBlue
        TourismCategory.ISLANDS -> MetroPurple
        TourismCategory.WATERFALLS_NATURE -> Color(0xFF38BDF8)
        TourismCategory.WILDLIFE_SAFARI -> Color(0xFFF59E0B)
    }
}
