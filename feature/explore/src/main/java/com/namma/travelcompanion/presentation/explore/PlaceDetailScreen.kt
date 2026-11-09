package com.namma.travelcompanion.presentation.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.GoldPro
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.ui.components.ScreenTopBar
import com.namma.travelcompanion.core.ui.components.openGeoCoordinates
import com.namma.travelcompanion.core.ui.components.openMapsSearch
import com.namma.travelcompanion.core.ui.components.openUrl
import com.namma.travelcompanion.presentation.explore.components.VideoCard
import com.namma.travelcompanion.presentation.explore.components.VisitEssentialsCard

/** One place: photo (Commons, credited), Wikipedia intro (credited), map button and matching channel videos. */
@Composable
fun PlaceDetailScreen(viewModel: PlaceDetailViewModel, onBack: () -> Unit, onReadSign: () -> Unit) {
    val state by viewModel.state.collectAsState()
    val place = state.place
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().background(Slate900)) {
        ScreenTopBar(
            title = place?.name ?: "",
            subtitle = listOfNotNull(state.category?.label, state.district?.name).joinToString(" · "),
            onBack = onBack
        )
        if (place == null) return@Column

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (place.hasImage) {
                AsyncImage(
                    model = place.imageUrl(width = 1000),
                    contentDescription = place.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().aspectRatio(4f / 3f).clip(RoundedCornerShape(14.dp))
                )
                SourceLink("Photo: Wikimedia Commons (see licence)") { openUrl(context, place.imageCreditUrl) }
            }
            state.essentials?.let { VisitEssentialsCard(it, onReadSign) }
            if (place.worldHeritage) Text("UNESCO World Heritage Site", color = GoldPro, fontSize = 13.sp, fontWeight = FontWeight.Bold)

            when {
                state.isLoadingSummary -> CircularProgressIndicator(color = AutoYellow)
                state.wikipediaSummary != null -> {
                    Text(state.wikipediaSummary!!, color = Slate200, fontSize = 15.sp, lineHeight = 22.sp)
                    SourceLink("From Wikipedia, CC BY-SA 4.0. Read more") { openUrl(context, place.wikipediaUrl) }
                }
                else -> Text(
                    text = place.description.ifBlank {
                        if (place.hasWikipedia) "Connect to the internet to read more about this place."
                        else "${state.category?.label ?: "Place"} in ${state.district?.name ?: "Karnataka"}."
                    },
                    color = Slate400,
                    fontSize = 15.sp
                )
            }

            Button(
                onClick = {
                    if (place.hasCoordinates) openGeoCoordinates(context, place.lat, place.lng, place.name)
                    else openMapsSearch(context, "${place.name}, ${state.district?.name ?: ""}, Karnataka")
                },
                colors = ButtonDefaults.buttonColors(containerColor = AutoYellow),
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) { Text("Directions in Maps", color = Slate900, fontWeight = FontWeight.Bold) }

            if (state.videos.isNotEmpty()) {
                Text("▶ My videos of ${place.name}", color = AutoYellow, fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 12.dp))
                state.videos.forEach { video ->
                    VideoCard(video, onClick = { openUrl(context, video.watchUrl) }, modifier = Modifier.fillMaxWidth())
                }
            }
            Text(
                text = when (place.source) {
                    "osm" -> "Place data © OpenStreetMap contributors (ODbL)"
                    "local" -> "Added by Namma Travel Companion"
                    else -> "Place data: Wikidata (CC0)"
                },
                color = Slate400,
                fontSize = 11.sp,
                modifier = Modifier.padding(top = 12.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SourceLink(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        color = MonsoonBlue,
        fontSize = 11.sp,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier.clickable(onClick = onClick)
    )
}
