package com.namma.travelcompanion.feature.commute.presentation.meter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MetroPurple
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.UserSubscriptionState
import com.namma.travelcompanion.core.ui.components.BangaloreBadge

/** Title row with the scan-quota / PRO badge. */
@Composable
internal fun MeterHeader(isPhoneMeterMode: Boolean, subscription: UserSubscriptionState) {
Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Column {
        Text(
            text = "Bengaluru Ride Meter",
            color = Slate200,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black
        )
        Text(
            text = if (isPhoneMeterMode) "Virtual AI Meter (When driver has no meter)" else "Camera OCR Scanner (Physical Meter)",
            color = Slate400,
            fontSize = 12.sp
        )
    }
    BangaloreBadge(
        text = if (subscription.isProUser) "PRO ACTIVE" else "${subscription.freeDailyScansRemaining} Scans Left",
        badgeColor = if (subscription.isProUser) AutoYellow else Slate700,
        textColor = if (subscription.isProUser) Slate900 else AutoYellow
    )
}
}

/** Phone-meter vs OCR-camera mode chips. */
@Composable
internal fun MeterModeSwitcher(
    isPhoneMeterMode: Boolean,
    onSelectPhoneMeter: () -> Unit,
    onSelectOcrCam: () -> Unit
) {
Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(8.dp)
) {
    FilterChip(
        selected = isPhoneMeterMode,
        onClick = { onSelectPhoneMeter() },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Speed, contentDescription = null, tint = if (isPhoneMeterMode) Slate900 else AutoYellow)
        },
        label = { Text("Phone Virtual Meter (Driver Has No Meter)", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = AutoYellow,
            selectedLabelColor = Slate900
        ),
        modifier = Modifier.weight(1f)
    )

    FilterChip(
        selected = !isPhoneMeterMode,
        onClick = { onSelectOcrCam() },
        leadingIcon = {
            Icon(imageVector = Icons.Default.CameraAlt, contentDescription = null, tint = if (!isPhoneMeterMode) Slate900 else Slate400)
        },
        label = { Text("OCR Cam", fontSize = 11.sp) },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MetroPurple,
            selectedLabelColor = Slate200
        )
    )
}
}
