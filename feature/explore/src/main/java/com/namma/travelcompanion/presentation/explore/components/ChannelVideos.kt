package com.namma.travelcompanion.presentation.explore.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.domain.model.ChannelVideo

/** The author's own YouTube videos; tapping one opens it in the YouTube app. */
@Composable
internal fun ChannelVideosRow(
    videos: List<ChannelVideo>,
    onOpenVideo: (ChannelVideo) -> Unit,
    onOpenChannel: () -> Unit
) {
    Column {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "▶ Travel videos by Raghunandan Kavi",
                color = AutoYellow,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            TextButton(onClick = onOpenChannel) { Text("Channel", color = AutoYellow) }
        }
        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            items(videos, key = { it.id }) { video -> VideoCard(video, onClick = { onOpenVideo(video) }) }
        }
    }
}

@Composable
internal fun VideoCard(video: ChannelVideo, onClick: () -> Unit, modifier: Modifier = Modifier.width(220.dp)) {
    Column(modifier = modifier.clickable(onClick = onClick)) {
        AsyncImage(
            model = video.thumbnailUrl,
            contentDescription = video.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(10.dp))
        )
        Text(
            text = video.title,
            color = Slate200,
            fontSize = 12.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
