package com.namma.travelcompanion.presentation.phrasebook.components

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
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.ui.components.BangaloreBadge

/** Title row with the remaining-plays badge. */
@Composable
internal fun SathiHeader(
    isProUser: Boolean,
    freePlaysRemaining: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "NammaBhasha Voice AI",
                color = Slate200,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = "Say anything in English ➔ Speaks in Fluent Kannada",
                color = Slate400,
                fontSize = 12.sp
            )
        }
        BangaloreBadge(
            text = if (isProUser) "UNLIMITED VOICE" else "${freePlaysRemaining} Plays Left",
            badgeColor = if (isProUser) AutoYellow else Slate700,
            textColor = if (isProUser) Slate900 else AutoYellow
        )
    }
}
