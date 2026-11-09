package com.namma.travelcompanion.presentation.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.ui.components.BangaloreBadge

/** Title row with the ESTIMATES badge. */
@Composable
internal fun RadarHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Bengaluru Ride Radar",
                color = Slate200,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = "Estimated fares across ride apps",
                color = Slate400,
                fontSize = 12.sp
            )
        }
        BangaloreBadge(
            text = "ESTIMATES",
            badgeColor = AutoYellow,
            textColor = Slate900
        )
    }
}
