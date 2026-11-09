package com.namma.travelcompanion.feature.commute.presentation.lens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
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
import com.namma.travelcompanion.feature.commute.domain.model.DetectedSignboardInfo
import com.namma.travelcompanion.core.ui.components.GlassCard

@Composable
internal fun SignboardDetailCard(
    sign: DetectedSignboardInfo,
    isSpeaking: Boolean,
    onPlayAnnouncement: () -> Unit,
    onDismiss: () -> Unit
) {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        cornerRadius = 20.dp,
        borderColor = MonsoonBlue
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header Row: Category Badge & Dismiss
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MonsoonBlue.copy(alpha = 0.2f),
                    border = BorderStroke(1.dp, MonsoonBlue)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = sign.category.iconTag, fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = sign.category.label,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MonsoonBlue
                        )
                    }
                }

                IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Dismiss",
                        tint = Slate400,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Kannada Script & English Title
            Text(
                text = sign.kannadaOriginal,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = AutoYellow
            )

            Text(
                text = sign.englishTitle,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Slate200,
                modifier = Modifier.padding(top = 2.dp)
            )

            Text(
                text = "Pronunciation: \"${sign.phoneticGuide}\"",
                fontSize = 11.sp,
                color = Slate400,
                modifier = Modifier.padding(top = 2.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Traveler Practical Advice Box
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = Slate800,
                border = BorderStroke(1.dp, Slate700)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "TRAVELER ACTION GUIDE:",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoGreen,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = sign.travelerAdvice,
                        fontSize = 11.sp,
                        color = Slate200,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Audio Listen Button
            Button(
                onClick = onPlayAnnouncement,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MonsoonBlue, contentColor = Slate900),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    imageVector = if (isSpeaking) Icons.Default.Stop else Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (isSpeaking) "Speaking..." else "Listen Audio Explanation",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
