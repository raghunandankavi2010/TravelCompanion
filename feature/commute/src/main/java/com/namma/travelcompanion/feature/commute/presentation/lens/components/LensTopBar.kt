package com.namma.travelcompanion.feature.commute.presentation.lens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Button
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
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900

import androidx.compose.foundation.layout.statusBarsPadding

@Composable
internal fun TopLensActionBar(
    isTorchEnabled: Boolean,
    isAutoAnnounceEnabled: Boolean,
    isSpeaking: Boolean,
    onToggleTorch: () -> Unit,
    onToggleAutoAnnounce: () -> Unit,
    onStopAudio: () -> Unit,
    onSwitchMode: () -> Unit,
    onReadSign: () -> Unit,
    onBack: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 14.dp, vertical = 10.dp),
        shape = RoundedCornerShape(20.dp),
        color = Slate900.copy(alpha = 0.88f),
        border = BorderStroke(1.dp, Slate700.copy(alpha = 0.7f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack, modifier = Modifier.size(36.dp)) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Slate200
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Namma Vision Lens",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate200
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "🚌", fontSize = 14.sp)
                    }
                    Text(
                        text = "Real-Time Bus & Signboard AI",
                        fontSize = 11.sp,
                        color = AutoYellow
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                // Torch Button
                IconButton(
                    onClick = onToggleTorch,
                    modifier = Modifier
                        .size(36.dp)
                        .background(if (isTorchEnabled) AutoYellow else Slate800, CircleShape)
                ) {
                    Icon(
                        imageVector = if (isTorchEnabled) Icons.Default.FlashOn else Icons.Default.FlashOff,
                        contentDescription = "Torch",
                        tint = if (isTorchEnabled) Slate900 else Slate400,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Auto-Voice Announcer Button
                IconButton(
                    onClick = onToggleAutoAnnounce,
                    modifier = Modifier
                        .size(36.dp)
                        .background(if (isAutoAnnounceEnabled) AutoGreen else Slate800, CircleShape)
                ) {
                    Icon(
                        imageVector = if (isSpeaking) Icons.Default.Stop else Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Voice Announce",
                        tint = if (isAutoAnnounceEnabled) Slate900 else Slate400,
                        modifier = Modifier.size(18.dp)
                    )
                }

                IconButton(
                    onClick = onReadSign,
                    modifier = Modifier
                        .size(36.dp)
                        .background(AutoYellow.copy(alpha = 0.25f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Translate,
                        contentDescription = "Read a sign",
                        tint = AutoYellow,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Directory Switcher
                IconButton(
                    onClick = onSwitchMode,
                    modifier = Modifier
                        .size(36.dp)
                        .background(MonsoonBlue.copy(alpha = 0.25f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "Route Directory",
                        tint = MonsoonBlue,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
