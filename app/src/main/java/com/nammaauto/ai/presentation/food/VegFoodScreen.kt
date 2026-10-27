package com.nammaauto.ai.presentation.food

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.AsyncImage
import com.nammaauto.ai.core.theme.AutoGreen
import com.nammaauto.ai.core.theme.AutoYellow
import com.nammaauto.ai.core.theme.FastMeterAlert
import com.nammaauto.ai.core.theme.MonsoonBlue
import com.nammaauto.ai.core.theme.Slate200
import com.nammaauto.ai.core.theme.Slate400
import com.nammaauto.ai.core.theme.Slate700
import com.nammaauto.ai.core.theme.Slate800
import com.nammaauto.ai.core.theme.Slate900
import com.nammaauto.ai.domain.model.PureVegType
import com.nammaauto.ai.domain.model.TempleAnnadanaGuidance
import com.nammaauto.ai.domain.model.VegCategory
import com.nammaauto.ai.domain.model.VegRestaurant

@Composable
fun VegFoodScreen(viewModel: VegFoodViewModel) {
    val context = LocalContext.current
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val restaurants by viewModel.filteredRestaurants.collectAsState()
    val selectedAnnadana by viewModel.selectedAnnadana.collectAsState()
    val isSpeaking by viewModel.isSpeaking.collectAsState()

    var showPhrasesModal by remember { mutableStateOf(false) }

    // Temple Annadana Info Dialog
    selectedAnnadana?.let { annadana ->
        AnnadanaDetailDialog(
            guidance = annadana,
            onDismiss = { viewModel.selectAnnadanaGuidance(null) }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header
            item {
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
                        modifier = Modifier.clickable { showPhrasesModal = !showPhrasesModal }
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

            // Quick Kannada food phrases banner
            item {
                AnimatedVisibility(visible = showPhrasesModal) {
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
                                    onClick = { showPhrasesModal = false },
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
                                        onClick = { viewModel.playKannadaOrderingPhrase(kannada) },
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

            // Sacred Temple Annadana Spotlight Bar
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.selectAnnadanaGuidance(viewModel.annadanaGuidelines[0]) },
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

            // Search Bar
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.setSearchQuery(it) },
                    placeholder = {
                        Text(
                            text = "Search Thatte Idli, Benne Dosa, highway or city...",
                            color = Slate400,
                            fontSize = 13.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = AutoGreen
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotBlank()) {
                            IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                Icon(Icons.Default.Close, null, tint = Slate400)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Slate800,
                        unfocusedContainerColor = Slate800,
                        focusedBorderColor = AutoGreen,
                        unfocusedBorderColor = Slate700,
                        focusedTextColor = Slate200,
                        unfocusedTextColor = Slate200
                    ),
                    singleLine = true
                )
            }

            // Category Filter Chips
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedCategory == null,
                        onClick = { viewModel.selectCategory(null) },
                        label = { Text("All Pure Veg (${restaurants.size})", fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AutoGreen,
                            selectedLabelColor = Slate900,
                            containerColor = Slate800,
                            labelColor = Slate400
                        ),
                        border = BorderStroke(1.dp, if (selectedCategory == null) AutoGreen else Slate700)
                    )

                    VegCategory.values().forEach { cat ->
                        val isSelected = selectedCategory == cat
                        FilterChip(
                            selected = isSelected,
                            onClick = { viewModel.selectCategory(if (isSelected) null else cat) },
                            label = { Text(cat.label, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = AutoGreen,
                                selectedLabelColor = Slate900,
                                containerColor = Slate800,
                                labelColor = Slate400
                            ),
                            border = BorderStroke(1.dp, if (isSelected) AutoGreen else Slate700)
                        )
                    }
                }
            }

            // Restaurant Cards
            items(restaurants, key = { it.id }) { restaurant ->
                VegRestaurantCard(
                    restaurant = restaurant,
                    onNavigateToLocation = {
                        openMapsNavigation(context, restaurant.mapsSearchQuery)
                    },
                    onAnnadanaClick = {
                        val guidance = viewModel.annadanaGuidelines.firstOrNull { it.templeName.contains(restaurant.district, ignoreCase = true) || restaurant.name.contains(it.templeName, ignoreCase = true) }
                            ?: viewModel.annadanaGuidelines[0]
                        viewModel.selectAnnadanaGuidance(guidance)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun VegRestaurantCard(
    restaurant: VegRestaurant,
    onNavigateToLocation: () -> Unit,
    onAnnadanaClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column {
            // Image with Overlays
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
            ) {
                AsyncImage(
                    model = restaurant.imageUrl,
                    contentDescription = restaurant.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                )

                // Gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Slate900.copy(alpha = 0.9f)),
                                startY = 80f
                            )
                        )
                )

                // Pure Veg Badge & Cleanliness
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.TopStart),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(restaurant.vegType.badgeColorHex),
                        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.4f))
                    ) {
                        Text(
                            text = "✓ " + restaurant.vegType.label,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Slate900.copy(alpha = 0.85f),
                        border = BorderStroke(1.dp, AutoYellow)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Star, null, tint = AutoYellow, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "${restaurant.cleanlinessRating}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate200
                            )
                        }
                    }
                }

                // Title & Highway / Location at bottom of photo
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.BottomStart)
                ) {
                    Text(
                        text = restaurant.name,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "${restaurant.kannadaName} • ${restaurant.district}",
                        fontSize = 11.sp,
                        color = AutoYellow
                    )
                }
            }

            // Card Body
            Column(modifier = Modifier.padding(14.dp)) {
                // Highway or Corridors
                Text(
                    text = "📍 " + restaurant.highwayOrLocation,
                    fontSize = 11.sp,
                    color = MonsoonBlue,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Signature Dishes Pills
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    restaurant.signatureDishes.forEach { dish ->
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = Slate900,
                            border = BorderStroke(1.dp, Slate700)
                        ) {
                            Text(
                                text = dish,
                                fontSize = 10.sp,
                                color = Slate200,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Pro Tip
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Slate900.copy(alpha = 0.6f),
                    border = BorderStroke(1.dp, Slate700.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "💡 Traveller Pro-Tip:",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = AutoYellow
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = restaurant.proTip,
                            fontSize = 11.sp,
                            color = Slate400,
                            lineHeight = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Cost & Timings Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        if (restaurant.isFreeTempleAnnadana) {
                            Text(
                                text = "FREE Sacred Annadana",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = AutoGreen
                            )
                        } else {
                            Text(
                                text = "Avg ~₹${restaurant.approxCostPerPersonInr} / person",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = AutoYellow
                            )
                        }
                        Text(
                            text = "🕒 " + restaurant.timings,
                            fontSize = 10.sp,
                            color = Slate400
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        if (restaurant.isFreeTempleAnnadana) {
                            OutlinedButton(
                                onClick = onAnnadanaClick,
                                border = BorderStroke(1.dp, AutoGreen),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Meal Rules", fontSize = 11.sp, color = AutoGreen)
                            }
                        }

                        Button(
                            onClick = onNavigateToLocation,
                            colors = ButtonDefaults.buttonColors(containerColor = AutoGreen),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Navigation,
                                contentDescription = null,
                                tint = Slate900,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Maps",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate900
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnnadanaDetailDialog(
    guidance: TempleAnnadanaGuidance,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .clip(RoundedCornerShape(20.dp)),
            color = Slate900,
            border = BorderStroke(1.dp, AutoGreen)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = guidance.templeName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = AutoGreen
                        )
                        Text(
                            text = guidance.diningHallName,
                            fontSize = 12.sp,
                            color = AutoYellow
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, null, tint = Slate400)
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = Slate700)

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    AnnadanaFactRow(label = "🕒 Lunch Hours", value = guidance.lunchTimings)
                    AnnadanaFactRow(label = "🌙 Dinner Hours", value = guidance.dinnerTimings)
                    AnnadanaFactRow(label = "👗 Dress Code", value = guidance.dressCode)
                    AnnadanaFactRow(label = "💰 Cost", value = guidance.costNotes)
                    AnnadanaFactRow(label = "🍲 Prasada Menu", value = guidance.mealDescription)
                    AnnadanaFactRow(label = "🙏 Sacred Etiquette", value = guidance.etiquetteTip)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = AutoGreen),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Understood & Close", color = Slate900, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun AnnadanaFactRow(label: String, value: String) {
    Column {
        Text(text = label, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoYellow)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = value, fontSize = 12.sp, color = Slate200, lineHeight = 16.sp)
    }
}

fun openMapsNavigation(context: Context, query: String) {
    try {
        val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(query))
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(mapIntent)
        } else {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(query)))
            context.startActivity(webIntent)
        }
    } catch (e: Exception) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(query)))
        context.startActivity(webIntent)
    }
}
