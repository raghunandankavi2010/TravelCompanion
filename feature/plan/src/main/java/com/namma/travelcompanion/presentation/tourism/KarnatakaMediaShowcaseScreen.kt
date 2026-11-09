package com.namma.travelcompanion.presentation.tourism


import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.domain.model.KarnatakaRegion
import com.namma.travelcompanion.domain.model.KarnatakaSpot
import com.namma.travelcompanion.domain.model.TourismCategory
import com.namma.travelcompanion.domain.repository.KarnatakaTourismRepository
import com.namma.travelcompanion.core.ui.components.openUrl
import com.namma.travelcompanion.presentation.tourism.components.CopyrightFreeBanner
import com.namma.travelcompanion.presentation.tourism.components.FullscreenPhotoDialog
import com.namma.travelcompanion.presentation.tourism.components.MediaCategoryFilterRow
import com.namma.travelcompanion.presentation.tourism.components.MediaDestinationCard
import com.namma.travelcompanion.presentation.tourism.components.MediaRegionFilterRow
import com.namma.travelcompanion.presentation.tourism.components.MediaShowcaseHeader

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
                openUrl(context, videoUrl)
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
            item {
                MediaShowcaseHeader()
            }

            item {
                CopyrightFreeBanner()
            }

            item {
                MediaRegionFilterRow(selectedRegion = selectedRegion, onSelect = { selectedRegion = it })
            }

            item {
                MediaCategoryFilterRow(
                    selectedCategory = selectedCategory,
                    totalCount = filteredSpots.size,
                    onSelect = { selectedCategory = it }
                )
            }

            // Cards with Photos & Video Launch Buttons
            items(filteredSpots, key = { it.id }) { spot ->
                MediaDestinationCard(
                    spot = spot,
                    onOpenFullscreen = { selectedSpotForFullscreen = spot },
                    onWatchVideo = {
                        val videoUrl = if (spot.videoTourUrl.isNotBlank()) spot.videoTourUrl
                        else "https://www.youtube.com/results?search_query=karnataka+tourism+" + Uri.encode(spot.name)
                        openUrl(context, videoUrl)
                    },
                    onOpenBooking = {
                        openUrl(context, spot.bookingInfo.portalUrl)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
