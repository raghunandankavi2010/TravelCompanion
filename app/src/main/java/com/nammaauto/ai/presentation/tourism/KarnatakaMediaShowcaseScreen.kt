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
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.nammaauto.ai.domain.repository.KarnatakaTourismRepository
import com.nammaauto.ai.presentation.components.GlassCard

@Composable
fun KarnatakaMediaShowcaseScreen() {
    val context = LocalContext.current
    var selectedRegion by remember { mutableStateOf(KarnatakaRegion.ALL) }
    var selectedSpotForVideo by remember { mutableStateOf<KarnatakaSpot?>(null) }

    val spotsWithMedia = KarnatakaTourismRepository.spots.filter { it.imageUrl.isNotBlank() }
    val filteredSpots = if (selectedRegion == KarnatakaRegion.ALL) spotsWithMedia
    else spotsWithMedia.filter { it.region == selectedRegion }

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
                            text = "Visual Karnataka Gallery",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate200
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "📸", fontSize = 22.sp)
                    }
                    Text(
                        text = "Curated HD Photos & Official 4K Video Documentary Tours",
                        fontSize = 12.sp,
                        color = AutoYellow
                    )
                }
            }

            // Region Scroll Filter
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

            // Video Tours Spotlight Banner
            item {
                GlassCard(
                    backgroundColor = Slate800.copy(alpha = 0.95f),
                    borderColor = MonsoonBlue.copy(alpha = 0.5f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(MonsoonBlue.copy(alpha = 0.2f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.SmartDisplay,
                                contentDescription = null,
                                tint = MonsoonBlue,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Experience Karnataka Before You Arrive",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate200
                            )
                            Text(
                                text = "Tap 'Watch 4K Video Tour' to open cinematic aerial walkthroughs by Dept of Tourism.",
                                fontSize = 11.sp,
                                color = Slate400
                            )
                        }
                    }
                }
            }

            // Cards with Photos & Video Launch Buttons
            items(filteredSpots, key = { it.id }) { spot ->
                MediaDestinationCard(
                    spot = spot,
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
                    .height(200.dp)
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
                                colors = listOf(Color.Transparent, Slate900.copy(alpha = 0.85f)),
                                startY = 100f
                            )
                        )
                )

                // Region & Category pill
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.TopStart)
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Slate900.copy(alpha = 0.8f),
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
                        text = spot.district + " • " + spot.category.label,
                        fontSize = 11.sp,
                        color = Slate200
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
                        Text(text = "👨‍🏫 Guide: " + spot.guideInfo, fontSize = 10.sp, color = AutoGreen)
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
