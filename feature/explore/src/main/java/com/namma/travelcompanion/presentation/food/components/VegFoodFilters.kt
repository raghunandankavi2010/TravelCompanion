package com.namma.travelcompanion.presentation.food.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.VegCategory

/** Free-text search field for restaurants, dishes, highways and cities. */
@Composable
internal fun VegSearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = {
            Text(
                text = "Search Thatte Idli, Benne Dosa, highway or city...",
                color = Slate400,
                fontSize = 13.sp
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = AutoGreen
            )
        },
        trailingIcon = {
            if (query.isNotBlank()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(Icons.Default.Close, null, tint = Slate400)
                }
            }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Slate800,
            unfocusedContainerColor = Slate800,
            focusedBorderColor = AutoGreen,
            unfocusedBorderColor = Slate700,
            focusedTextColor = Slate200,
            unfocusedTextColor = Slate200
        ),
        singleLine = true
    )
}

/** Horizontally scrolling category chips ("All" plus one per [VegCategory]). */
@Composable
internal fun VegCategoryChips(
    selectedCategory: VegCategory?,
    totalCount: Int,
    onSelect: (VegCategory?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = selectedCategory == null,
            onClick = { onSelect(null) },
            label = { Text("All Pure Veg ($totalCount)", fontSize = 12.sp) },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = AutoGreen,
                selectedLabelColor = Slate900,
                containerColor = Slate800,
                labelColor = Slate400
            ),
            border = BorderStroke(1.dp, if (selectedCategory == null) AutoGreen else Slate700)
        )

        VegCategory.values().forEach { cat ->
            val isSelected = selectedCategory == cat
            FilterChip(
                selected = isSelected,
                onClick = { onSelect(if (isSelected) null else cat) },
                label = { Text(cat.label, fontSize = 12.sp) },
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
