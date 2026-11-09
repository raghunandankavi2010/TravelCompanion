package com.namma.travelcompanion.feature.commute.presentation.scraper.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.OpenInBrowser
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
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.network.api.ScrapedSpotInfo

/** Wikipedia result card: photo, extract and open-in actions. */
@Composable
fun ScrapedResultCard(
    info: ScrapedSpotInfo,
    onOpenWeb: () -> Unit,
    onOpenMaps: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, AutoGreen.copy(alpha = 0.8f))
    ) {
        Column {
            if (info.thumbnailUrl.isNotBlank()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                ) {
                    AsyncImage(
                        model = info.thumbnailUrl,
                        contentDescription = info.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    )

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

                    // License tag
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Slate900.copy(alpha = 0.85f),
                        border = BorderStroke(1.dp, AutoGreen),
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.TopStart)
                    ) {
                        Text(
                            text = "✓ " + info.copyrightLicense,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = AutoGreen,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    // Scraped Status
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = AutoYellow,
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.TopEnd)
                    ) {
                        Text(
                            text = if (info.isLiveScraped) "🌐 From Wikipedia" else "📦 Offline copy",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        Text(
                            text = info.title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = info.description,
                            fontSize = 11.sp,
                            color = Slate200
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(14.dp)) {
                if (info.thumbnailUrl.isBlank()) {
                    Text(
                        text = info.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate200
                    )
                    Text(
                        text = info.description,
                        fontSize = 12.sp,
                        color = AutoYellow
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Text(
                    text = info.extract,
                    fontSize = 12.sp,
                    color = Slate200,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Slate900,
                    border = BorderStroke(1.dp, Slate700)
                ) {
                    Text(
                        text = "🕒 " + info.fetchedTimestamp,
                        fontSize = 10.sp,
                        color = MonsoonBlue,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onOpenWeb,
                        colors = ButtonDefaults.buttonColors(containerColor = MonsoonBlue),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.OpenInBrowser, null, tint = Slate900, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Read Article", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate900)
                    }

                    OutlinedButton(
                        onClick = onOpenMaps,
                        border = BorderStroke(1.dp, AutoYellow),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.Map, null, tint = AutoYellow, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Google Maps", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AutoYellow)
                    }
                }
            }
        }
    }
}
