package com.namma.travelcompanion.presentation.phrasebook.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.KannadaPhrase

/** One phrase with its play button. */
@Composable
internal fun PhraseItemCard(
    phrase: KannadaPhrase,
    isPlaying: Boolean,
    onPlay: () -> Unit
) {
    val borderColor by animateColorAsState(
        targetValue = if (isPlaying) AutoYellow else Slate700,
        label = "border"
    )

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = Slate800,
        border = BorderStroke(1.dp, borderColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                // Kannada Script
                Text(
                    text = phrase.kannadaLipi,
                    color = AutoYellow,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                // Romanized Phonetics
                Text(
                    text = phrase.phoneticEnglish,
                    color = Slate200,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(2.dp))
                // English Meaning
                Text(
                    text = "“${phrase.englishMeaning}”",
                    color = Slate400,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "💡 ${phrase.contextTip}",
                    color = MonsoonBlue,
                    fontSize = 10.sp
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            // Audio Playback Button
            Surface(
                modifier = Modifier
                    .size(46.dp)
                    .clickable { onPlay() },
                shape = CircleShape,
                color = if (isPlaying) AutoYellow else Slate700,
                border = BorderStroke(1.dp, if (isPlaying) AutoYellow else Slate400)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Speak Phrase",
                        tint = if (isPlaying) Slate900 else Slate200,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}
