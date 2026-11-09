package com.namma.travelcompanion.presentation.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.data.repository.OpenPlacesRepository
import com.namma.travelcompanion.core.ui.components.openUrl
import com.namma.travelcompanion.presentation.explore.components.ChannelVideosRow
import com.namma.travelcompanion.presentation.explore.components.DistrictCard
import com.namma.travelcompanion.presentation.explore.components.KarnatakaMap
import com.namma.travelcompanion.presentation.explore.components.PlaceListItem
import com.namma.travelcompanion.presentation.explore.components.PlaceSearchField

/** Home: map and list of all 31 districts. Typing in search lists matching places across the state instead. */
@Composable
fun DistrictsHomeScreen(
    viewModel: DistrictsHomeViewModel,
    onOpenDistrict: (districtId: String) -> Unit,
    onOpenPlace: (placeId: String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Slate900).padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Text("Explore Karnataka", color = Slate200, fontSize = 24.sp, fontWeight = FontWeight.Black)
                Text("Tap a district on the map or in the list", color = Slate400, fontSize = 12.sp)
            }
        }
        item { PlaceSearchField(state.query, viewModel::setQuery) }

        if (state.query.isNotBlank()) {
            item { Text("${state.searchResults.size} places found", color = Slate400, fontSize = 12.sp) }
            items(state.searchResults, key = { it.id }) { place ->
                PlaceListItem(place, onClick = { onOpenPlace(place.id) })
            }
        } else {
            item { KarnatakaMap(state.shapes, onDistrictClick = onOpenDistrict) }
            if (state.channelVideos.isNotEmpty()) {
                item {
                    ChannelVideosRow(
                        videos = state.channelVideos,
                        onOpenVideo = { openUrl(context, it.watchUrl) },
                        onOpenChannel = { openUrl(context, OpenPlacesRepository.CHANNEL_URL) }
                    )
                }
            }
            item {
                Text(
                    text = "All 31 districts, south to north",
                    color = AutoYellow,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
            items(state.districts, key = { it.first.id }) { (district, count) ->
                DistrictCard(district, count, onClick = { onOpenDistrict(district.id) })
            }
        }
        item {
            Text(
                text = "Places: Wikidata (CC0) and © OpenStreetMap contributors (ODbL). Map: geoBoundaries (ODbL). Photos: Wikimedia Commons.",
                color = Slate400,
                fontSize = 11.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
