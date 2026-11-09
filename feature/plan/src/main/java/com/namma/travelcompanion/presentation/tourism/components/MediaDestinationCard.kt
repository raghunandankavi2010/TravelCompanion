package com.namma.travelcompanion.presentation.tourism.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.KarnatakaSpot

@Composable
internal fun MediaDestinationCard(
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
