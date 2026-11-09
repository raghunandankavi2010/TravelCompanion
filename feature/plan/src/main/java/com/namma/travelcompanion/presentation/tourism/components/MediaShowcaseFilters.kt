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
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.KarnatakaRegion
import com.namma.travelcompanion.domain.model.TourismCategory

@Composable
internal fun MediaShowcaseHeader() {
    Spacer(modifier = Modifier.height(16.dp))
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Visual Karnataka Showcase",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Slate200
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "📸", fontSize = 22.sp)
        }
        Text(
            text = "Copyright-Free Wikimedia/Unsplash Media & 4K Aerial Video Tours",
            fontSize = 12.sp,
            color = AutoYellow
        )
    }
}

@Composable
internal fun CopyrightFreeBanner() {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = AutoGreen.copy(alpha = 0.15f),
        border = BorderStroke(1.dp, AutoGreen.copy(alpha = 0.5f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Verified,
                contentDescription = null,
                tint = AutoGreen,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "100% Royalty-Free & Non-Copyrighted Media",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoGreen
                )
                Text(
                    text = "All media licensed under Creative Commons CC BY-SA 4.0 or Unsplash Open License. Safe for travel guides & presentations.",
                    fontSize = 10.sp,
                    color = Slate200
                )
            }
        }
    }
}

@Composable
internal fun MediaRegionFilterRow(
    selectedRegion: KarnatakaRegion,
    onSelect: (KarnatakaRegion) -> Unit
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        KarnatakaRegion.values().forEach { region ->
            val isSelected = selectedRegion == region
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (isSelected) AutoYellow else Slate800,
                border = BorderStroke(1.dp, if (isSelected) AutoYellow else Slate700),
                modifier = Modifier.clickable { onSelect(region) }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = region.iconTag, fontSize = 13.sp)
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

@Composable
internal fun MediaCategoryFilterRow(
    selectedCategory: TourismCategory?,
    totalCount: Int,
    onSelect: (TourismCategory?) -> Unit
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
            label = { Text("All Categories (${totalCount})", fontSize = 11.sp) },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MonsoonBlue,
                selectedLabelColor = Slate900,
                containerColor = Slate800,
                labelColor = Slate400
            ),
            border = BorderStroke(1.dp, if (selectedCategory == null) MonsoonBlue else Slate700)
        )

        TourismCategory.values().forEach { cat ->
            val isSelected = selectedCategory == cat
            FilterChip(
                selected = isSelected,
                onClick = { onSelect(if (isSelected) null else cat) },
                label = { Text(cat.label, fontSize = 11.sp) },
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
