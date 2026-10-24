package com.nammaauto.ai.presentation.phrasebook

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nammaauto.ai.core.theme.AutoGreen
import com.nammaauto.ai.core.theme.AutoYellow
import com.nammaauto.ai.core.theme.MetroPurple
import com.nammaauto.ai.core.theme.Slate200
import com.nammaauto.ai.core.theme.Slate400
import com.nammaauto.ai.core.theme.Slate700
import com.nammaauto.ai.core.theme.Slate800
import com.nammaauto.ai.core.theme.Slate900
import com.nammaauto.ai.domain.model.KannadaPhrase
import com.nammaauto.ai.domain.model.PhraseCategory
import com.nammaauto.ai.presentation.components.BangaloreBadge
import com.nammaauto.ai.presentation.components.GlassCard

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
    val hagglingAsk by viewModel.hagglingAsk.collectAsState()

    val filteredPhrases = viewModel.getFilteredPhrases()

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
                    text = "NammaBhasha Sathi",
                    color = Slate200,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "Bengaluru Auto Negotiation Assistant",
                    color = Slate400,
                    fontSize = 12.sp
                )
            }
            BangaloreBadge(
                text = if (subscription.isProUser) "UNLIMITED AUDIO" else "${subscription.freeDailyPhrasesRemaining} Plays Left",
                badgeColor = if (subscription.isProUser) AutoYellow else Slate700,
                textColor = if (subscription.isProUser) Slate900 else AutoYellow
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Dynamic Auto-Haggling Bot Card
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(
                    text = "SMART COUNTER-OFFER GENERATOR",
                    color = AutoYellow,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
                Text(
                    text = "Driver quoting arbitrary price? Let AI generate the counter offer:",
                    color = Slate400,
                    fontSize = 11.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = hagglingAsk,
                        onValueChange = { viewModel.setHagglingAsk(it) },
                        label = { Text("Driver Asked (₹)") },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AutoYellow,
                            unfocusedBorderColor = Slate700,
                            focusedTextColor = Slate200,
                            unfocusedTextColor = Slate200
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    val askNum = hagglingAsk.toIntOrNull() ?: 150
                    val fairOffer = kotlin.math.max(30, (askNum * 0.65).toInt())
                    Button(
                        onClick = {
                            val success = viewModel.playCustomHagglingPhrase(askNum)
                            if (!success) {
                                Toast.makeText(context, "Free daily audio plays used. Upgrade to Pro!", Toast.LENGTH_SHORT).show()
                                onNavigateToProStore()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = AutoGreen),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.VolumeUp, contentDescription = null, tint = Slate900)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Offer ₹$fairOffer", color = Slate900, fontWeight = FontWeight.Black)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Horizontal Category Tabs
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

        // Phrase Cards List
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
                    color = com.nammaauto.ai.core.theme.MonsoonBlue,
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
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = "Speak Phrase",
                        tint = if (isPlaying) Slate900 else Slate200,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}
