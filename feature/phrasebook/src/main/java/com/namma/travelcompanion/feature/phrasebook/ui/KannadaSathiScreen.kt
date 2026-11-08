package com.namma.travelcompanion.feature.phrasebook.ui

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.MetroPurple
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.feature.phrasebook.domain.model.KannadaPhrase
import com.namma.travelcompanion.feature.phrasebook.domain.model.PhraseCategory
import com.namma.travelcompanion.presentation.components.BangaloreBadge
import com.namma.travelcompanion.presentation.components.GlassCard

@Composable
fun KannadaSathiScreen(
    viewModel: KannadaSathiViewModel,
    onNavigateToProStore: () -> Unit
) {
    val context = LocalContext.current
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val subscription by viewModel.subscriptionState.collectAsState()
    val isSpeaking by viewModel.isSpeaking.collectAsState()
    val lastSpokenId by viewModel.lastSpokenPhraseId.collectAsState()
    val customInput by viewModel.customEnglishInput.collectAsState()
    val activeTranslation by viewModel.activeTranslation.collectAsState()
    val isTranslating by viewModel.isTranslating.collectAsState()
    val isListeningToMic by viewModel.isListeningToMic.collectAsState()

    var hasAudioPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val audioPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasAudioPermission = granted
        if (granted) viewModel.startVoiceMicInput()
    }

    val filteredPhrases = viewModel.getFilteredPhrases()

    val quickEnglishPrompts = listOf(
        "Wait 2 minutes please",
        "Do you have change for 500?",
        "Drop me near the main gate",
        "Please reduce music volume",
        "Can we take the flyover?",
        "I will pay via Google Pay",
        "Please drive slowly"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
            .padding(16.dp)
    ) {
        // Header
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
                text = if (subscription.isProUser) "UNLIMITED VOICE" else "${subscription.freeDailyPhrasesRemaining} Plays Left",
                badgeColor = if (subscription.isProUser) AutoYellow else Slate700,
                textColor = if (subscription.isProUser) Slate900 else AutoYellow
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Custom English Voice / Text Translation Copilot
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

                // Input Bar with Mic button and Send button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = customInput,
                        onValueChange = { viewModel.setCustomEnglishInput(it) },
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
                        onClick = {
                            if (hasAudioPermission) {
                                if (isListeningToMic) viewModel.stopVoiceMicInput()
                                else viewModel.startVoiceMicInput()
                            } else {
                                audioPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                            }
                        },
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
                        onClick = { viewModel.translateCustomPhrase(customInput) },
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

                // Quick Clickable English Commute Chips
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    quickEnglishPrompts.forEach { prompt ->
                        SuggestionChip(
                            onClick = {
                                viewModel.translateCustomPhrase(prompt, andSpeakImmediately = false)
                            },
                            label = { Text(prompt, fontSize = 11.sp, color = Slate200) },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = Slate800
                            ),
                            border = BorderStroke(1.dp, Slate700)
                        )
                    }
                }

                // Live Translated Result Banner with Large "Speak to Driver" Button
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
                                    onClick = {
                                        val success = viewModel.playActiveTranslation()
                                        if (!success) {
                                            Toast.makeText(context, "Free voice quota reached. Upgrade to Pro!", Toast.LENGTH_SHORT).show()
                                            onNavigateToProStore()
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isSpeaking && lastSpokenId == "custom_translation") AutoGreen else AutoYellow
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
                                            text = if (isSpeaking && lastSpokenId == "custom_translation") "Playing to Driver..." else "Speak Aloud to Auto Driver 🔊",
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
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Horizontal Category Tabs for Standard Phrasebook
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            FilterChip(
                selected = selectedCategory == null,
                onClick = { viewModel.selectCategory(null) },
                label = { Text("All Phrases", fontSize = 11.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = AutoYellow,
                    selectedLabelColor = Slate900
                )
            )
            PhraseCategory.values().forEach { cat ->
                FilterChip(
                    selected = selectedCategory == cat,
                    onClick = { viewModel.selectCategory(cat) },
                    label = { Text(cat.label, fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MetroPurple,
                        selectedLabelColor = Slate200
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Standard Categorized Phrases List
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredPhrases) { phrase ->
                PhraseItemCard(
                    phrase = phrase,
                    isPlaying = isSpeaking && lastSpokenId == phrase.id,
                    onPlay = {
                        val success = viewModel.playPhrase(phrase)
                        if (!success) {
                            Toast.makeText(context, "Daily free audio quota reached. Upgrade to Pro!", Toast.LENGTH_SHORT).show()
                            onNavigateToProStore()
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun PhraseItemCard(
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
