package com.namma.travelcompanion.feature.commute.presentation.lens.components

import androidx.camera.core.Camera
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namma.travelcompanion.core.theme.AutoGreen
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.MonsoonBlue
import com.namma.travelcompanion.core.theme.Slate200
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate700
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.feature.commute.domain.model.BusRouteInfo
import com.namma.travelcompanion.feature.commute.domain.model.BusSignLensDatabase
import com.namma.travelcompanion.feature.commute.domain.model.ConductorPhrase
import com.namma.travelcompanion.core.ui.components.GlassCard

@Composable
internal fun RouteDirectoryView(
    searchQuery: String,
    onSearchChanged: (String) -> Unit,
    onSelectRoute: (BusRouteInfo?) -> Unit,
    selectedRoute: BusRouteInfo?,
    onPlayRoute: (BusRouteInfo) -> Unit,
    onSpeakPhrase: (ConductorPhrase) -> Unit,
    onBackToCamera: () -> Unit
) {
    val allRoutes = BusSignLensDatabase.routes
    val filteredRoutes = remember(searchQuery) {
        if (searchQuery.isBlank()) allRoutes
        else {
            val q = searchQuery.trim().lowercase()
            allRoutes.filter { route ->
                route.routeNumber.lowercase().contains(q) ||
                        route.originEn.lowercase().contains(q) ||
                        route.destinationEn.lowercase().contains(q) ||
                        route.viaStopsEn.any { it.lowercase().contains(q) } ||
                        route.serviceCategory.lowercase().contains(q)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackToCamera) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Camera",
                        tint = AutoYellow
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Column {
                    Text(
                        text = "Transit Route Directory",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate200
                    )
                    Text(
                        text = "BMTC, Vayu Vajra & KSRTC Express",
                        fontSize = 11.sp,
                        color = Slate400
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = AutoYellow.copy(alpha = 0.2f),
                border = BorderStroke(1.dp, AutoYellow),
                modifier = Modifier.clickable { onBackToCamera() }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = null,
                        tint = AutoYellow,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Live Lens",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoYellow
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Search Field
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search by route (335-E, KIA-8) or stop (Airport, Silk Board)...", fontSize = 12.sp) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Slate400
                )
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Slate800,
                unfocusedContainerColor = Slate800,
                focusedBorderColor = AutoYellow,
                unfocusedBorderColor = Slate700,
                focusedTextColor = Slate200,
                unfocusedTextColor = Slate200
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredRoutes) { route ->
                RouteListItemCard(
                    route = route,
                    onPlayAnnouncement = { onPlayRoute(route) },
                    onSpeakPhrase = onSpeakPhrase
                )
            }
        }
    }
}

@Composable
private fun RouteListItemCard(
    route: BusRouteInfo,
    onPlayAnnouncement: () -> Unit,
    onSpeakPhrase: (ConductorPhrase) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        cornerRadius = 16.dp,
        borderColor = Color(route.badgeColorHex)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(route.badgeColorHex)
                ) {
                    Text(
                        text = route.routeNumber,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = Slate900,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        fontFamily = FontFamily.Monospace
                    )
                }

                Text(
                    text = route.approxFareRange,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoGreen
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "${route.originEn} ➔ ${route.destinationEn}",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Slate200
            )

            Text(
                text = "${route.originKn} ➔ ${route.destinationKn}",
                fontSize = 11.sp,
                color = Slate400
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Via Stops
            Text(
                text = "Via: ${route.viaStopsEn.take(4).joinToString(", ")}...",
                fontSize = 10.sp,
                color = AutoYellow
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onPlayAnnouncement,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Slate700, contentColor = Slate200),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Announce", fontSize = 11.sp)
                }

                OutlinedButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, MonsoonBlue),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MonsoonBlue)
                ) {
                    Text(if (expanded) "Hide Phrases" else "Conductor Helper", fontSize = 11.sp)
                }
            }

            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    HorizontalDivider(color = Slate700)
                    route.conductorSurvivalPhrases.forEach { phrase ->
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = Slate800,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSpeakPhrase(phrase) }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(phrase.englishMeaning, fontSize = 11.sp, color = Slate200)
                                    Text(phrase.kannadaLipi, fontSize = 11.sp, color = AutoYellow)
                                }
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                    contentDescription = null,
                                    tint = AutoYellow,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
