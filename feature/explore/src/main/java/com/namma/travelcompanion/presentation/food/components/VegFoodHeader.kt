package com.namma.travelcompanion.presentation.food.components

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate800

/** Screen title row with the "Food Voice" toggle. */
@Composable
internal fun VegFoodHeader(onTogglePhrases: () -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Pure Veg Dining",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate200
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "🍲", fontSize = 22.sp)
            }
            Text(
                text = "Highway Pitstops • Temple Annadana • Udupi Darshinis",
                fontSize = 12.sp,
                color = AutoGreen
            )
        }

        // Food phrases button
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = AutoYellow.copy(alpha = 0.15f),
            border = BorderStroke(1.dp, AutoYellow),
            modifier = Modifier.clickable { onTogglePhrases() }
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = null,
                    tint = AutoYellow,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Food Voice",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoYellow
                )
            }
        }
    }
}

/** Collapsible Kannada food-ordering phrases card. */
@Composable
internal fun FoodPhrasesBanner(
    visible: Boolean,
    onClose: () -> Unit,
    onPlayPhrase: (String) -> Unit
) {
    AnimatedVisibility(visible = visible) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Slate800),
            border = BorderStroke(1.dp, AutoYellow.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🗣️ On-Location Kannada Food Order Assistant",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoYellow
                    )
                    IconButton(
                        onClick = onClose,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(Icons.Default.Close, null, tint = Slate400)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                val foodPhrases = listOf(
                    Pair("ಇದು ಪ್ಯೂರ್ ವೆಜಿಟೇರಿಯನ್ ಹೋಟೆಲ್ ಅಲ್ವಾ?", "Idu pure vegetarian hotel alwa? (Is this 100% pure veg?)"),
                    Pair("ಸ್ವಲ್ಪ ತುಪ್ಪ ಹಾಕಿ ಕೊಡಿ", "Swalpa tuppa haaki kodi (Please add a spoon of pure ghee)"),
                    Pair("ಖಾರ ಬೇಡ, ಕಮ್ಮಿ ಮಾಡಿ", "Khaara beda, kammi maadi (No spice please, make it mild)"),
                    Pair("ಒಂದು ಫಿಲ್ಟರ್ ಕಾಫಿ ಕೊಡಿ", "Ondu filter coffee kodi (Please give one strong filter coffee)"),
                    Pair("ಜೋಳದ ರೊಟ್ಟಿ ಊಟ ಇದೆಯಾ?", "Jolada rotti oota ideya? (Is traditional Jolada Rotti meal available?)")
                )

                foodPhrases.forEach { (kannada, phonetic) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = kannada, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Slate200)
                            Text(text = phonetic, fontSize = 11.sp, color = Slate400)
                        }
                        IconButton(
                            onClick = { onPlayPhrase(kannada) },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                contentDescription = null,
                                tint = AutoYellow,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

/** Highlighted card that opens the temple annadana guidance. */
@Composable
internal fun AnnadanaSpotlightCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        border = BorderStroke(1.dp, AutoGreen.copy(alpha = 0.6f))
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = AutoGreen.copy(alpha = 0.2f),
                modifier = Modifier.size(42.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.VolunteerActivism,
                        contentDescription = null,
                        tint = AutoGreen,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Sacred Temple Annadana (Free Meals)",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoGreen
                )
                Text(
                    text = "Dharmasthala • Sringeri • Horanadu • Udupi Matha meal times & sacred etiquette",
                    fontSize = 11.sp,
                    color = Slate400
                )
            }
            Text(text = "View ❯", fontSize = 12.sp, color = AutoYellow, fontWeight = FontWeight.Bold)
        }
    }
}
