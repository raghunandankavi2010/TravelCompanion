package com.namma.travelcompanion.presentation.phrasebook

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.presentation.phrasebook.components.CategoryTabs
import com.namma.travelcompanion.presentation.phrasebook.components.CustomTranslatorCard
import com.namma.travelcompanion.presentation.phrasebook.components.PhraseItemCard
import com.namma.travelcompanion.presentation.phrasebook.components.SathiHeader

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
        SathiHeader(
            isProUser = subscription.isProUser,
            freePlaysRemaining = subscription.freeDailyPhrasesRemaining
        )

        Spacer(modifier = Modifier.height(14.dp))

        CustomTranslatorCard(
            customInput = customInput,
            isTranslating = isTranslating,
            isListeningToMic = isListeningToMic,
            quickPrompts = quickEnglishPrompts,
            activeTranslation = activeTranslation,
            isPlayingTranslation = isSpeaking && lastSpokenId == "custom_translation",
            onInputChange = { viewModel.setCustomEnglishInput(it) },
            onMicClick = {
                if (hasAudioPermission) {
                    if (isListeningToMic) viewModel.stopVoiceMicInput()
                    else viewModel.startVoiceMicInput()
                } else {
                    audioPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                }
            },
            onTranslate = { viewModel.translateCustomPhrase(customInput) },
            onQuickPrompt = { prompt ->
                viewModel.translateCustomPhrase(prompt, andSpeakImmediately = false)
            },
            onSpeakTranslation = {
                val success = viewModel.playActiveTranslation()
                if (!success) {
                    Toast.makeText(context, "Free voice quota reached. Upgrade to Pro!", Toast.LENGTH_SHORT).show()
                    onNavigateToProStore()
                }
            }
        )

        Spacer(modifier = Modifier.height(14.dp))

        CategoryTabs(
            selectedCategory = selectedCategory,
            onSelect = viewModel::selectCategory
        )

        Spacer(modifier = Modifier.height(10.dp))

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
