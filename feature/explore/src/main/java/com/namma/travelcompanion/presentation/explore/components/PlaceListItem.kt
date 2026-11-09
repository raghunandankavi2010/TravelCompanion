package com.namma.travelcompanion.presentation.explore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.GoldPro
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.domain.model.KarnatakaDistricts
import com.namma.travelcompanion.domain.model.OpenPlace
import com.namma.travelcompanion.domain.model.PlaceCategory
import com.namma.travelcompanion.core.ui.components.GlassCard

@Composable
internal fun PlaceListItem(place: OpenPlace, onClick: () -> Unit) {
    GlassCard(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)) {
        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            PlaceThumbnail(place)
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(place.name, color = Slate200, fontSize = 15.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(
                    text = listOfNotNull(
                        PlaceCategory.of(place)?.label ?: place.category,
                        KarnatakaDistricts.byId(place.districtId)?.name
                    ).joinToString(" · "),
                    color = AutoYellow,
                    fontSize = 11.sp
                )
                if (place.worldHeritage) Text("UNESCO World Heritage", color = GoldPro, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                if (place.description.isNotBlank()) {
                    Text(place.description, color = Slate400, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}

@Composable
private fun PlaceThumbnail(place: OpenPlace) {
    val shape = RoundedCornerShape(10.dp)
    if (place.hasImage) {
        AsyncImage(
            model = place.imageUrl(width = 200),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(64.dp).clip(shape)
        )
    } else {
        Box(modifier = Modifier.size(64.dp).clip(shape).background(Slate700), contentAlignment = Alignment.Center) {
            Icon(Icons.Default.Place, contentDescription = null, tint = Slate400)
        }
    }
}
