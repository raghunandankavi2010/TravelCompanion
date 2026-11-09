package com.namma.travelcompanion.presentation.phrasebook.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MetroPurple
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.PhraseCategory

/** Horizontal category tabs for the standard phrasebook. */
@Composable
internal fun CategoryTabs(
    selectedCategory: PhraseCategory?,
    onSelect: (PhraseCategory?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        FilterChip(
            selected = selectedCategory == null,
            onClick = { onSelect(null) },
            label = { Text("All Phrases", fontSize = 11.sp) },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = AutoYellow,
                selectedLabelColor = Slate900
            )
        )
        PhraseCategory.values().forEach { cat ->
            FilterChip(
                selected = selectedCategory == cat,
                onClick = { onSelect(cat) },
                label = { Text(cat.label, fontSize = 11.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MetroPurple,
                    selectedLabelColor = Slate200
                )
            )
        }
    }
}
