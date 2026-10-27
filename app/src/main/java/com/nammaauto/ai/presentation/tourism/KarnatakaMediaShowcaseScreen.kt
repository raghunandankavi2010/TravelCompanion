package com.nammaauto.ai.presentation.tourism

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.nammaauto.ai.core.theme.MonsoonBlue
import com.nammaauto.ai.core.theme.Slate200
import com.nammaauto.ai.core.theme.Slate400
import com.nammaauto.ai.core.theme.Slate700
import com.nammaauto.ai.core.theme.Slate800
import com.nammaauto.ai.core.theme.Slate900
import com.nammaauto.ai.domain.model.KarnatakaRegion
import com.nammaauto.ai.domain.model.KarnatakaSpot
import com.nammaauto.ai.domain.model.TourismCategory
import com.nammaauto.ai.domain.repository.KarnatakaTourismRepository
import com.nammaauto.ai.presentation.components.GlassCard

@Composable
fun KarnatakaMediaShowcaseScreen() {
    val context = LocalContext.current
    var selectedRegion by remember { mutableStateOf(KarnatakaRegion.ALL) }
    var selectedCategory by remember { mutableStateOf<TourismCategory?>(null) }
    var selectedSpotForFullscreen by remember { mutableStateOf<KarnatakaSpot?>(null) }

    val spotsWithMedia = KarnatakaTourismRepository.spots.filter { it.imageUrl.isNotBlank() }
    val filteredSpots = spotsWithMedia.filter { spot ->
        val matchesRegion = selectedRegion == KarnatakaRegion.ALL || spot.region == selectedRegion
        val matchesCategory = selectedCategory == null || spot.category == selectedCategory
        matchesRegion && matchesCategory
    }

    // Fullscreen Image Dialog
    selectedSpotForFullscreen?.let { spot ->
        FullscreenPhotoDialog(
            spot = spot,
            onDismiss = { selectedSpotForFullscreen = null },
            onWatchVideo = {
                val videoUrl = if (spot.videoTourUrl.isNotBlank()) spot.videoTourUrl
                else "https://www.youtube.com/results?search_query=karnataka+tourism+" + Uri.encode(spot.name)
                openBrowserUrl(context, videoUrl)
            }
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Visual Karnataka Showcase",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate200
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "📸", fontSize = 22.sp)
                    }
                    Text(
                        text = "Copyright-Free Wikimedia/Unsplash Media & 4K Aerial Video Tours",
                        fontSize = 12.sp,
                        color = AutoYellow
                    )
                }
            }

            // Copyright-Free Guarantee Card
            item {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = AutoGreen.copy(alpha = 0.15f),
                    border = BorderStroke(1.dp, AutoGreen.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = AutoGreen,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "100% Royalty-Free & Non-Copyrighted Media",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = AutoGreen
                            )
                            Text(
                                text = "All media licensed under Creative Commons CC BY-SA 4.0 or Unsplash Open License. Safe for travel guides & presentations.",
                                fontSize = 10.sp,
                                color = Slate200
                            )
                        }
                    }
                }
            }

            // Region Filter Row
            item {
                val scrollState = rememberScrollState()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(scrollState),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    KarnatakaRegion.values().forEach { region ->
                        val isSelected = selectedRegion == region
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) AutoYellow else Slate800,
                            border = BorderStroke(1.dp, if (isSelected) AutoYellow else Slate700),
                            modifier = Modifier.clickable { selectedRegion = region }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = region.iconTag, fontSize = 13.sp)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = region.label,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Slate900 else Slate200
                                )
                            }
                        }
                    }
                }
            }

            // Category Filter Row
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    FilterChip(
                        selected = selectedCategory == null,
                        onClick = { selectedCategory = null },
                        label = { Text("All Categories (${filteredSpots.size})", fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MonsoonBlue,
                            selectedLabelColor = Slate900,
                            containerColor = Slate800,
                            labelColor = Slate400
                        ),
                        border = BorderStroke(1.dp, if (selectedCategory == null) MonsoonBlue else Slate700)
                    )

                    TourismCategory.values().forEach { cat ->
                        val isSelected = selectedCategory == cat
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedCategory = if (isSelected) null else cat },
                            label = { Text(cat.label, fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MonsoonBlue,
                                selectedLabelColor = Slate900,
                                containerColor = Slate800,
                                labelColor = Slate400
                            ),
                            border = BorderStroke(1.dp, if (isSelected) MonsoonBlue else Slate700)
                        )
                    }
                }
            }

            // Cards with Photos & Video Launch Buttons
            items(filteredSpots, key = { it.id }) { spot ->
                MediaDestinationCard(
                    spot = spot,
                    onOpenFullscreen = { selectedSpotForFullscreen = spot },
                    onWatchVideo = {
                        val videoUrl = if (spot.videoTourUrl.isNotBlank()) spot.videoTourUrl
                        else "https://www.youtube.com/results?search_query=karnataka+tourism+" + Uri.encode(spot.name)
                        openBrowserUrl(context, videoUrl)
                    },
                    onOpenBooking = {
                        openBrowserUrl(context, spot.bookingInfo.portalUrl)
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
fun MediaDestinationCard(
    spot: KarnatakaSpot,
    onOpenFullscreen: () -> Unit,
    onWatchVideo: () -> Unit,
    onOpenBooking: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column {
            // Photo with Gradient Overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .clickable { onOpenFullscreen() }
            ) {
                AsyncImage(
                    model = spot.imageUrl,
                    contentDescription = spot.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                )

                // Dark gradient overlay on bottom of image for readability
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Slate900.copy(alpha = 0.9f)),
                                startY = 90f
                            )
                        )
                )

                // Top Pills: Region & Non-Copyrighted License
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .align(Alignment.TopStart),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Slate900.copy(alpha = 0.85f),
                        border = BorderStroke(1.dp, AutoYellow)
                    ) {
                        Text(
                            text = spot.region.iconTag + " " + spot.region.label,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = AutoYellow,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = AutoGreen,
                        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.4f))
                    ) {
                        Text(
                            text = "✓ CC BY-SA 4.0",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                // Title & Subtitle at bottom of image
                Column(
                    modifier = Modifier
                        .padding(14.dp)
                        .align(Alignment.BottomStart)
                ) {
                    Text(
                        text = spot.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "${spot.kannadaName} • ${spot.district} • ${spot.category.label}",
                        fontSize = 11.sp,
                        color = AutoYellow
                    )
                }
            }

            // Details and actions
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = spot.tagline,
                    fontSize = 12.sp,
                    color = MonsoonBlue,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = spot.overview,
                    fontSize = 12.sp,
                    color = Slate400,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Guide & Pricing Summary
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    color = Slate900,
                    border = BorderStroke(1.dp, Slate700)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "🇮🇳 Domestic: " + (if (spot.foreignerPricing.domesticFeeInr == 0) "Free" else "₹${spot.foreignerPricing.domesticFeeInr}"), fontSize = 11.sp, color = Slate200)
                            Text(text = "🌍 Foreigner: " + (if (spot.foreignerPricing.foreignerFeeInr == 0) "Free" else "${spot.foreignerPricing.approxUsdEquivalent}"), fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoYellow)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "👨‍🏫 Verified Guide: " + spot.guideInfo, fontSize = 10.sp, color = AutoGreen)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onWatchVideo,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF4444)), // YouTube Red
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Watch 4K Tour",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    OutlinedButton(
                        onClick = onOpenBooking,
                        border = BorderStroke(1.dp, AutoYellow),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Book Tickets",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = AutoYellow
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FullscreenPhotoDialog(
    spot: KarnatakaSpot,
    onDismiss: () -> Unit,
    onWatchVideo: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Slate900.copy(alpha = 0.96f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Top close button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = spot.name, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Slate200)
                        Text(text = "License: Creative Commons CC BY-SA 4.0 / Wikimedia", fontSize = 11.sp, color = AutoGreen)
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, null, tint = Slate200, modifier = Modifier.size(28.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Image in Center
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    AsyncImage(
                        model = spot.imageUrl,
                        contentDescription = spot.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = spot.overview,
                    fontSize = 12.sp,
                    color = Slate400,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onWatchVideo,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF4444)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.PlayArrow, null, tint = Color.White)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Watch 4K Cinematic Tour", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
