package com.namma.travelcompanion.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel,
    onNavigateToExplore: () -> Unit,
    onNavigateToVegFood: () -> Unit
) {
    val spotIds by viewModel.favoriteSpotIds.collectAsState()
    val restaurantNames by viewModel.favoriteRestaurantNames.collectAsState()

    val savedSpots = viewModel.allSpots.filter { spotIds.contains(it.id) }
    val savedRestaurants = viewModel.allRestaurants.filter { restaurantNames.contains(it.name) }

    var selectedTab by remember { mutableStateOf(0) } // 0 = Spots, 1 = Veg Food

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
    ) {
        // Header
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Slate800,
            tonalElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Bookmark,
                        contentDescription = null,
                        tint = AutoYellow
                    )
                    Text(
                        text = "Saved Bookmarks & Itinerary",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Your offline-saved Karnataka tourist gems and pure vegetarian highway oases.",
                    fontSize = 13.sp,
                    color = Slate400
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { selectedTab = 0 },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedTab == 0) AutoYellow else Slate700,
                            contentColor = if (selectedTab == 0) Slate900 else Color.White
                        )
                    ) {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Spots (${savedSpots.size})")
                    }

                    Button(
                        onClick = { selectedTab = 1 },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedTab == 1) AutoYellow else Slate700,
                            contentColor = if (selectedTab == 1) Slate900 else Color.White
                        )
                    ) {
                        Icon(imageVector = Icons.Default.Restaurant, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Dining (${savedRestaurants.size})")
                    }
                }
            }
        }

        // Content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (selectedTab == 0) {
                if (savedSpots.isEmpty()) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Slate400,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "No saved tourist spots yet.",
                            color = Slate400,
                            fontSize = 15.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = onNavigateToExplore,
                            colors = ButtonDefaults.buttonColors(containerColor = AutoYellow, contentColor = Slate900)
                        ) {
                            Text(text = "Explore Karnataka Spots")
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(savedSpots) { spot ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Slate800)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = spot.name,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "${spot.district} • ${spot.region.name}",
                                            color = AutoYellow,
                                            fontSize = 13.sp
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = spot.tagline,
                                            color = Slate400,
                                            fontSize = 13.sp
                                        )
                                    }
                                    IconButton(
                                        onClick = { viewModel.toggleSpotFavorite(spot.id) }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Remove",
                                            tint = FastMeterAlert
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (savedRestaurants.isEmpty()) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Restaurant,
                            contentDescription = null,
                            tint = Slate400,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "No saved veg dining spots yet.",
                            color = Slate400,
                            fontSize = 15.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = onNavigateToVegFood,
                            colors = ButtonDefaults.buttonColors(containerColor = AutoYellow, contentColor = Slate900)
                        ) {
                            Text(text = "Browse Veg Dining Directory")
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(savedRestaurants) { restaurant ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Slate800)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = restaurant.name,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "${restaurant.district} • ${restaurant.highwayOrLocation}",
                                            color = AutoYellow,
                                            fontSize = 13.sp
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "Specialties: ${restaurant.signatureDishes.joinToString(", ")}",
                                            color = Slate400,
                                            fontSize = 13.sp
                                        )
                                    }
                                    IconButton(
                                        onClick = { viewModel.toggleRestaurantFavorite(restaurant.name) }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Remove",
                                            tint = FastMeterAlert
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
