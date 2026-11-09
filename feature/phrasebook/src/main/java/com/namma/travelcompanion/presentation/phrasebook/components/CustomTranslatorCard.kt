package com.namma.travelcompanion.presentation.phrasebook.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
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
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.data.translator.TranslationResult
import com.namma.travelcompanion.core.ui.components.GlassCard

/** Card with the custom English phrase translator. */
@Composable
internal fun CustomTranslatorCard(
    customInput: String,
    isTranslating: Boolean,
    isListeningToMic: Boolean,
    quickPrompts: List<String>,
    activeTranslation: TranslationResult?,
    isPlayingTranslation: Boolean,
    onInputChange: (String) -> Unit,
    onMicClick: () -> Unit,
    onTranslate: () -> Unit,
    onQuickPrompt: (String) -> Unit,
    onSpeakTranslation: () -> Unit
) {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        borderColor = if (isListeningToMic) FastMeterAlert else AutoYellow.copy(alpha = 0.6f)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Translate,
                        contentDescription = null,
                        tint = AutoYellow,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "CUSTOM PHRASE TRANSLATOR",
                        color = AutoYellow,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.5.sp
                    )
                }

                if (isTranslating) {
                    CircularProgressIndicator(
                        color = AutoYellow,
                        modifier = Modifier.size(16.dp),
                        strokeWidth = 2.dp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            TranslatorInputBar(
                customInput = customInput,
                isListeningToMic = isListeningToMic,
                onInputChange = onInputChange,
                onMicClick = onMicClick,
                onTranslate = onTranslate
            )

            Spacer(modifier = Modifier.height(10.dp))
            QuickPromptChips(prompts = quickPrompts, onPrompt = onQuickPrompt)

            TranslationResultBanner(
                activeTranslation = activeTranslation,
                isPlaying = isPlayingTranslation,
                onSpeak = onSpeakTranslation
            )
        }
    }
}

/** Text field with the mic and translate buttons. */
@Composable
private fun TranslatorInputBar(
    customInput: String,
    isListeningToMic: Boolean,
    onInputChange: (String) -> Unit,
    onMicClick: () -> Unit,
    onTranslate: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = customInput,
            onValueChange = { onInputChange(it) },
            placeholder = { Text("Type or speak in English...", color = Slate400, fontSize = 13.sp) },
            modifier = Modifier.weight(1f),
            singleLine = false,
            maxLines = 2,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AutoYellow,
                unfocusedBorderColor = Slate700,
                focusedTextColor = Slate200,
                unfocusedTextColor = Slate200,
                cursorColor = AutoYellow
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Microphone Voice Input Button
        IconButton(
            onClick = onMicClick,
            modifier = Modifier
                .size(46.dp)
                .background(
                    if (isListeningToMic) FastMeterAlert else Slate800,
                    CircleShape
                )
        ) {
            Icon(
                imageVector = if (isListeningToMic) Icons.Default.MicOff else Icons.Default.Mic,
                contentDescription = "Voice Input",
                tint = if (isListeningToMic) Slate200 else AutoYellow
            )
        }

        Spacer(modifier = Modifier.width(6.dp))

        // Translate Button
        IconButton(
            onClick = { onTranslate() },
            modifier = Modifier
                .size(46.dp)
                .background(AutoGreen, CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Translate",
                tint = Slate900
            )
        }
    }
}

/** Quick English commute phrase chips. */
@Composable
private fun QuickPromptChips(
    prompts: List<String>,
    onPrompt: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        prompts.forEach { prompt ->
            SuggestionChip(
                onClick = { onPrompt(prompt) },
                label = { Text(prompt, fontSize = 11.sp, color = Slate200) },
                colors = SuggestionChipDefaults.suggestionChipColors(
                    containerColor = Slate800
                ),
                border = BorderStroke(1.dp, Slate700)
            )
        }
    }
}

/** Translated result with the large Speak to Driver button. */
@Composable
private fun TranslationResultBanner(
    activeTranslation: TranslationResult?,
    isPlaying: Boolean,
    onSpeak: () -> Unit
) {
    AnimatedVisibility(
        visible = activeTranslation != null,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        activeTranslation?.let { result ->
            Spacer(modifier = Modifier.height(12.dp))
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = Slate900,
                border = BorderStroke(1.dp, AutoYellow)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = result.sourceEngine.uppercase(),
                            color = MonsoonBlue,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "KANNADA TRANSLATION",
                            color = AutoYellow,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Kannada Lipi
                    Text(
                        text = result.kannadaLipi,
                        color = AutoYellow,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Phonetics
                    Text(
                        text = "🗣️ ${result.phoneticPronunciation}",
                        color = Slate200,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Speak to Driver Button
                    Button(
                        onClick = onSpeak,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isPlaying) AutoGreen else AutoYellow
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                contentDescription = null,
                                tint = Slate900
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isPlaying) "Playing to Driver..." else "Speak Aloud to Auto Driver 🔊",
                                color = Slate900,
                                fontWeight = FontWeight.Black,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
