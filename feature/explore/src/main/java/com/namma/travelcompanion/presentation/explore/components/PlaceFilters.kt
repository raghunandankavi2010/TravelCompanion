package com.namma.travelcompanion.presentation.explore.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Attractions
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.BeachAccess
import androidx.compose.material.icons.filled.Castle
import androidx.compose.material.icons.filled.Hiking
import androidx.compose.material.icons.filled.Kayaking
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material.icons.filled.Water
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.layout.size
import com.namma.travelcompanion.domain.model.PlaceCategory
import androidx.compose.ui.unit.dp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate900

@Composable
internal fun PlaceSearchField(query: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Search any place, e.g. Iruppu Falls", color = Slate400) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Slate400) },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Slate200,
            unfocusedTextColor = Slate200,
            focusedBorderColor = AutoYellow,
            unfocusedBorderColor = Slate700
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

/** "All" plus one chip per category present in this district, with how many places each has. */
@Composable
internal fun PlaceCategoryChips(
    counts: List<Pair<PlaceCategory, Int>>,
    total: Int,
    selected: PlaceCategory?,
    onSelect: (PlaceCategory?) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        item { CategoryChip("All ($total)", null, selected == null) { onSelect(null) } }
        items(counts, key = { it.first.name }) { (category, count) ->
            CategoryChip("${category.label} ($count)", category.icon, selected == category) { onSelect(category) }
        }
    }
}

@Composable
private fun CategoryChip(label: String, icon: ImageVector?, isSelected: Boolean, onClick: () -> Unit) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = { Text(label) },
        leadingIcon = icon?.let { { Icon(it, contentDescription = null, modifier = Modifier.size(18.dp)) } },
        colors = FilterChipDefaults.filterChipColors(
            labelColor = Slate200,
            iconColor = AutoYellow,
            selectedContainerColor = AutoYellow,
            selectedLabelColor = Slate900,
            selectedLeadingIconColor = Slate900
        )
    )
}

val PlaceCategory.icon: ImageVector
    get() = when (this) {
        PlaceCategory.TEMPLES -> Icons.Default.TempleHindu
        PlaceCategory.WATERFALLS -> Icons.Default.Water
        PlaceCategory.WILDLIFE -> Icons.Default.Pets
        PlaceCategory.HILLS -> Icons.Default.Hiking
        PlaceCategory.FORTS -> Icons.Default.Castle
        PlaceCategory.HERITAGE -> Icons.Default.AccountBalance
        PlaceCategory.LAKES -> Icons.Default.Kayaking
        PlaceCategory.BEACHES -> Icons.Default.BeachAccess
        PlaceCategory.GARDENS -> Icons.Default.Park
        PlaceCategory.MUSEUMS -> Icons.Default.Museum
        PlaceCategory.AMUSEMENT -> Icons.Default.Attractions
        PlaceCategory.OTHER -> Icons.Default.Place
    }
