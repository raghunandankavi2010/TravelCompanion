package com.namma.travelcompanion.presentation.lifeline.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.HelplineCategory
import com.namma.travelcompanion.core.ui.components.BangaloreBadge

/** Screen title with the CITIZEN SOS badge. */
@Composable
internal fun LifelineHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Karnataka Lifeline & Safety Hub",
                color = Slate200,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = "24x7 State Emergency, Hospitals, Flybus & Auto Grievance",
                color = Slate400,
                fontSize = 12.sp
            )
        }
        BangaloreBadge(
            text = "CITIZEN SOS",
            badgeColor = FastMeterAlert,
            textColor = Slate200
        )
    }
}

/** Horizontal category chips that switch the helpline tab. */
@Composable
internal fun HelplineCategoryChips(
    selectedTab: HelplineCategory,
    onSelect: (HelplineCategory) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        HelplineCategory.values().forEach { cat ->
            FilterChip(
                selected = selectedTab == cat,
                onClick = { onSelect(cat) },
                label = { Text(cat.label, fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = if (cat == HelplineCategory.POLICE_SAFETY) FastMeterAlert else AutoYellow,
                    selectedLabelColor = if (cat == HelplineCategory.POLICE_SAFETY) Slate200 else Slate900
                )
            )
        }
    }
}
