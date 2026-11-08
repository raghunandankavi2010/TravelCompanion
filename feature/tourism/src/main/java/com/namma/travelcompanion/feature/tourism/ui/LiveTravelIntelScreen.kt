package com.namma.travelcompanion.feature.tourism.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.FastMeterAlert
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.theme.SurgeWarningOrange
import com.namma.travelcompanion.feature.tourism.data.api.LiveTravelAdvisory
import com.namma.travelcompanion.feature.tourism.data.api.LiveWeatherBrief
import com.namma.travelcompanion.feature.tourism.data.api.ScrapedSpotInfo

@Composable
fun LiveTravelIntelScreen(viewModel: LiveTravelScraperViewModel) {
    val context = LocalContext.current
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isScraping by viewModel.isScraping.collectAsState()
    val scrapedResult by viewModel.scrapedResult.collectAsState()
    val isSyncingAdvisories by viewModel.isSyncingAdvisories.collectAsState()
    val lastSyncTime by viewModel.lastSyncTime.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Live Travel Intel",
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate200
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "🌐", fontSize = 22.sp)
                        }
                        Text(
                            text = "Real-Time Net Scraper • Weather • Forest Alerts",
                            fontSize = 12.sp,
                            color = MonsoonBlue
                        )
                    }

                    // Refresh Button
                    IconButton(
                        onClick = { viewModel.refreshAdvisories() },
                        enabled = !isSyncingAdvisories
                    ) {
                        if (isSyncingAdvisories) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = AutoYellow,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Refresh",
                                tint = AutoYellow
                            )
                        }
                    }
                }
            }

            // Real-Time Net Scraper Search Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Slate800),
                    border = BorderStroke(1.dp, MonsoonBlue.copy(alpha = 0.6f))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "🔍 Scrape Real-Time Travel Info from the Net",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = MonsoonBlue
                        )
                        Text(
                            text = "Fetches live summaries, copyright-free media, and coordinates for any Karnataka place.",
                            fontSize = 11.sp,
                            color = Slate400
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = searchQuery,
                                onValueChange = { viewModel.setSearchQuery(it) },
                                placeholder = { Text("Enter town, temple or peak...", fontSize = 12.sp, color = Slate400) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(10.dp),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = Slate900,
                                    unfocusedContainerColor = Slate900,
                                    focusedBorderColor = MonsoonBlue,
                                    unfocusedBorderColor = Slate700,
                                    focusedTextColor = Slate200,
                                    unfocusedTextColor = Slate200
                                )
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = { viewModel.scrapeDestination(searchQuery) },
                                enabled = !isScraping && searchQuery.isNotBlank(),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MonsoonBlue)
                            ) {
                                if (isScraping) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(16.dp),
                                        color = Slate900,
                                        strokeWidth = 2.dp
                                    )
                                } else {
                                    Text("Scrape", fontWeight = FontWeight.Bold, color = Slate900, fontSize = 12.sp)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Preset Quick Chips
                        Text(text = "Quick Live Queries:", fontSize = 11.sp, color = Slate400)
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            viewModel.presetChips.forEach { chip ->
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Slate900,
                                    border = BorderStroke(1.dp, Slate700),
                                    modifier = Modifier.clickable { viewModel.scrapeDestination(chip) }
                                ) {
                                    Text(
                                        text = chip,
                                        fontSize = 10.sp,
                                        color = AutoYellow,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Scraped Result Display Card
            item {
                scrapedResult?.let { info ->
                    ScrapedResultCard(
                        info = info,
                        onOpenWeb = { openWebUrl(context, info.pageUrl) },
                        onOpenMaps = {
                            if (info.latitude != null && info.longitude != null) {
                                openGeoCoordinates(context, info.latitude, info.longitude, info.title)
                            } else {
                                openSearchMaps(context, info.title)
                            }
                        }
                    )
                }
            }

            // Regional Live Weather Radar
            item {
                Text(
                    text = "⛅ Regional Live Weather Radar",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoYellow
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    viewModel.weatherBriefs.forEach { weather ->
                        WeatherBriefCard(weather = weather)
                    }
                }
            }

            // Official Gates, Crowds & Road Alerts
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🚨 Real-Time Gate & Permit Advisories",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = FastMeterAlert
                    )
                    Text(
                        text = "Verified Ground Truth",
                        fontSize = 10.sp,
                        color = Slate400
                    )
                }
            }

            items(viewModel.advisories, key = { it.id }) { advisory ->
                AdvisoryCard(advisory = advisory)
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

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
                            text = if (info.isLiveScraped) "⚡ Real-Time Web Stream" else "📦 Verified Archive",
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

@Composable
fun WeatherBriefCard(weather: LiveWeatherBrief) {
    Card(
        modifier = Modifier
            .width(240.dp)
            .height(150.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = weather.zoneName,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoYellow,
                    maxLines = 1
                )
                Text(
                    text = weather.tempCelsius,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate200
                )
            }

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.WbSunny, null, tint = AutoYellow, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = weather.condition, fontSize = 11.sp, color = Slate200)
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CloudQueue, null, tint = MonsoonBlue, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Rain Chance: ${weather.rainProbability}", fontSize = 10.sp, color = MonsoonBlue)
                }
            }

            Surface(
                shape = RoundedCornerShape(6.dp),
                color = Slate900,
                border = BorderStroke(1.dp, Slate700)
            ) {
                Text(
                    text = "💡 ${weather.advisoryTag}",
                    fontSize = 9.sp,
                    color = Slate400,
                    maxLines = 1,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                )
            }
        }
    }
}

@Composable
fun AdvisoryCard(advisory: LiveTravelAdvisory) {
    val borderColor = when (advisory.urgencyLevel) {
        "CRITICAL" -> FastMeterAlert
        "CAUTION" -> SurgeWarningOrange
        else -> AutoYellow
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = advisory.destination,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate200
                )
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = borderColor.copy(alpha = 0.2f),
                    border = BorderStroke(1.dp, borderColor)
                ) {
                    Text(
                        text = advisory.urgencyLevel,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = borderColor,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = advisory.title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = borderColor
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = advisory.groundTruthAdvice,
                fontSize = 11.sp,
                color = Slate400,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Source: ${advisory.lastVerified}",
                fontSize = 10.sp,
                color = Slate400
            )
        }
    }
}

fun openWebUrl(context: Context, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    } catch (_: Exception) {}
}

fun openGeoCoordinates(context: Context, lat: Double, lon: Double, label: String) {
    try {
        val uri = Uri.parse("geo:$lat,$lon?q=$lat,$lon(" + Uri.encode(label) + ")")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    } catch (_: Exception) {
        openSearchMaps(context, label)
    }
}

fun openSearchMaps(context: Context, query: String) {
    try {
        val uri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(query))
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    } catch (_: Exception) {}
}
