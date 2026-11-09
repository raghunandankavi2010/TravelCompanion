package com.namma.travelcompanion.presentation.food.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Star
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
import com.namma.travelcompanion.domain.model.VegRestaurant

/** Restaurant card: photo with badges, dishes, pro-tip, cost and action buttons. */
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
