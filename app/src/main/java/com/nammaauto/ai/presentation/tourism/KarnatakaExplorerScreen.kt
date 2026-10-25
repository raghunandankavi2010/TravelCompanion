package com.nammaauto.ai.presentation.tourism

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.BeachAccess
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Hiking
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Kayaking
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material.icons.filled.Train
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nammaauto.ai.core.theme.AutoGreen
import com.nammaauto.ai.core.theme.AutoGreenDark
import com.nammaauto.ai.core.theme.AutoYellow
import com.nammaauto.ai.core.theme.FastMeterAlert
import com.nammaauto.ai.core.theme.MetroPurple
import com.nammaauto.ai.core.theme.MonsoonBlue
import com.nammaauto.ai.core.theme.Slate200
import com.nammaauto.ai.core.theme.Slate400
import com.nammaauto.ai.core.theme.Slate700
import com.nammaauto.ai.core.theme.Slate800
import com.nammaauto.ai.core.theme.Slate900
import com.nammaauto.ai.core.theme.SurgeWarningOrange
import com.nammaauto.ai.domain.model.KarnatakaRegion
import com.nammaauto.ai.domain.model.KarnatakaSpot
import com.nammaauto.ai.domain.model.TourismCategory
import com.nammaauto.ai.domain.model.TouristGuidanceData
import com.nammaauto.ai.presentation.components.GlassCard

@Composable
fun KarnatakaExplorerScreen(
    viewModel: KarnatakaExplorerViewModel,
    onNavigateToPhrasebook: () -> Unit,
    onNavigateToProStore: () -> Unit
) {
    val context = LocalContext.current
    val selectedRegion by viewModel.selectedRegion.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isForeignerMode by viewModel.isForeignerMode.collectAsState()
    val filteredSpots by viewModel.filteredSpots.collectAsState()
    val selectedSpotForDetails by viewModel.selectedSpotForDetails.collectAsState()
    val isSpeaking by viewModel.isSpeaking.collectAsState()
    val lastSpokenSpotId by viewModel.lastSpokenSpotId.collectAsState()

    var showScamBusterTab by remember { mutableStateOf(false) }
    var showStatewideTransportTab by remember { mutableStateOf(false) }
    var showRegionalDialectTab by remember { mutableStateOf(false) }

    // Detail Dialog
    selectedSpotForDetails?.let { spot ->
        DestinationDetailDialog(
            spot = spot,
            isForeignerMode = isForeignerMode,
            isSpeaking = isSpeaking && lastSpokenSpotId == spot.id,
            onPlayAudio = { viewModel.playSpotKannadaPhrase(spot) },
            onStopAudio = { viewModel.stopAudio() },
            onDismiss = { viewModel.selectSpotForDetails(null) },
            onOpenBookingUrl = { openBrowserUrl(context, spot.bookingInfo.portalUrl) }
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
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header Section
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
                                text = "Karnataka Explorer",
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate200
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "🧭", fontSize = 22.sp)
                        }
                        Text(
                            text = "Statewide Tourism • Heritage, Ghats & Coast",
                            fontSize = 12.sp,
                            color = AutoYellow
                        )
                    }

                    // Foreigner USD Currency Toggle Pill
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = if (isForeignerMode) AutoYellow.copy(alpha = 0.2f) else Slate800,
                        border = BorderStroke(1.dp, if (isForeignerMode) AutoYellow else Slate700),
                        modifier = Modifier.clickable { viewModel.toggleForeignerMode() }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.CurrencyExchange,
                                contentDescription = "Currency",
                                tint = if (isForeignerMode) AutoYellow else Slate400,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isForeignerMode) "USD ($)" else "INR (₹)",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isForeignerMode) AutoYellow else Slate400
                            )
                        }
                    }
                }
            }

            // Region Selector Scroll (North, Coastal, Malnad, South, Deccan)
            item {
                val regionScrollState = rememberScrollState()
                Column {
                    Text(
                        text = "EXPLORE BY REGION / CIRCUIT:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate400,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(regionScrollState),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        KarnatakaRegion.values().forEach { region ->
                            val isSelected = selectedRegion == region
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = if (isSelected) AutoYellow else Slate800,
                                border = BorderStroke(1.dp, if (isSelected) AutoYellow else Slate700),
                                modifier = Modifier.clickable { viewModel.selectRegion(region) }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = region.iconTag, fontSize = 14.sp)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = region.label,
                                        fontSize = 12.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) Slate900 else Slate200
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Quick Statewide Info Hub Toggles (Transport Tariffs, Dialect, Scam Buster)
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Statewide Auto Tariffs Button
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { showStatewideTransportTab = !showStatewideTransportTab },
                        shape = RoundedCornerShape(12.dp),
                        color = if (showStatewideTransportTab) MonsoonBlue.copy(alpha = 0.25f) else Slate800,
                        border = BorderStroke(1.dp, if (showStatewideTransportTab) MonsoonBlue else Slate700)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.DirectionsBus,
                                    contentDescription = null,
                                    tint = MonsoonBlue,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "City Auto Rates",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Slate200
                                )
                            }
                            Text(
                                text = "Mangalore, Mysuru, Hubli",
                                fontSize = 10.sp,
                                color = Slate400
                            )
                        }
                    }

                    // Regional Dialects & Food Button
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { showRegionalDialectTab = !showRegionalDialectTab },
                        shape = RoundedCornerShape(12.dp),
                        color = if (showRegionalDialectTab) AutoGreen.copy(alpha = 0.25f) else Slate800,
                        border = BorderStroke(1.dp, if (showRegionalDialectTab) AutoGreen else Slate700)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Restaurant,
                                    contentDescription = null,
                                    tint = AutoGreen,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Dialects & Food",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Slate200
                                )
                            }
                            Text(
                                text = "Tulu, Malnad, Uttara KA",
                                fontSize = 10.sp,
                                color = Slate400
                            )
                        }
                    }

                    // Scam Buster Button
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { showScamBusterTab = !showScamBusterTab },
                        shape = RoundedCornerShape(12.dp),
                        color = if (showScamBusterTab) SurgeWarningOrange.copy(alpha = 0.25f) else Slate800,
                        border = BorderStroke(1.dp, if (showScamBusterTab) SurgeWarningOrange else Slate700)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Security,
                                    contentDescription = null,
                                    tint = SurgeWarningOrange,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Scam Buster",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Slate200
                                )
                            }
                            Text(
                                text = "MRP & Rules",
                                fontSize = 10.sp,
                                color = Slate400
                            )
                        }
                    }
                }
            }

            // Expandable: Statewide City Auto Tariffs & KSRTC Guide
            item {
                AnimatedVisibility(visible = showStatewideTransportTab) {
                    GlassCard(
                        backgroundColor = Slate800.copy(alpha = 0.95f),
                        borderColor = MonsoonBlue.copy(alpha = 0.5f)
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "🛺 All-Karnataka Auto Rickshaw & Transit Benchmarks",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AutoYellow
                                )
                                IconButton(
                                    onClick = { showStatewideTransportTab = false },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(Icons.Default.Close, contentDescription = "Close", tint = Slate400)
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))

                            viewModel.cityTariffs.forEach { tariff ->
                                Surface(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    shape = RoundedCornerShape(8.dp),
                                    color = Slate900,
                                    border = BorderStroke(1.dp, Slate700)
                                ) {
                                    Column(modifier = Modifier.padding(10.dp)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = tariff.cityName,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Slate200
                                            )
                                            Text(
                                                text = "${tariff.baseFare} | ${tariff.perKmRate}",
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = AutoGreen
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(text = tariff.localAdvice, fontSize = 11.sp, color = Slate400)
                                        Text(
                                            text = "🚌 Transit Option: ${tariff.sharedAutoOption}",
                                            fontSize = 11.sp,
                                            color = MonsoonBlue
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = { openBrowserUrl(context, "https://ksrtc.in") },
                                    colors = ButtonDefaults.buttonColors(containerColor = MonsoonBlue),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(Icons.Default.DirectionsBus, contentDescription = null, tint = Slate900, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Book KSRTC Buses", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Slate900)
                                }
                                OutlinedButton(
                                    onClick = { openBrowserUrl(context, "https://karnatakatourism.org") },
                                    border = BorderStroke(1.dp, AutoYellow),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("KSTDC Tours", fontSize = 11.sp, color = AutoYellow)
                                }
                            }
                        }
                    }
                }
            }

            // Expandable: Regional Dialects & Food Guide
            item {
                AnimatedVisibility(visible = showRegionalDialectTab) {
                    GlassCard(
                        backgroundColor = Slate800.copy(alpha = 0.95f),
                        borderColor = AutoGreen.copy(alpha = 0.5f)
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "🍛 Regional Dialects & Iconic Cuisines of Karnataka",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AutoYellow
                                )
                                IconButton(
                                    onClick = { showRegionalDialectTab = false },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(Icons.Default.Close, contentDescription = "Close", tint = Slate400)
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))

                            viewModel.regionalDialects.forEach { dialect ->
                                Surface(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    shape = RoundedCornerShape(8.dp),
                                    color = Slate900,
                                    border = BorderStroke(1.dp, Slate700)
                                ) {
                                    Column(modifier = Modifier.padding(10.dp)) {
                                        Text(
                                            text = dialect.dialectName,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = AutoGreen
                                        )
                                        Text(
                                            text = "Greeting: ${dialect.commonGreeting}",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Slate200
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "Famous Cuisines: ${dialect.regionalCuisines.joinToString(", ")}",
                                            fontSize = 11.sp,
                                            color = MonsoonBlue
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "💡 Local Tip: ${dialect.localTip}",
                                            fontSize = 11.sp,
                                            color = Slate400
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Expandable: Scam Buster & Price Benchmarks
            item {
                AnimatedVisibility(visible = showScamBusterTab) {
                    GlassCard(
                        backgroundColor = Slate800.copy(alpha = 0.95f),
                        borderColor = SurgeWarningOrange.copy(alpha = 0.5f)
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "🛡️ Karnataka Tourist Scam Buster & Fair Prices",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AutoYellow
                                )
                                IconButton(
                                    onClick = { showScamBusterTab = false },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(Icons.Default.Close, contentDescription = "Close", tint = Slate400)
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))

                            TouristGuidanceData.priceBenchmarks.forEach { item ->
                                Surface(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 3.dp),
                                    shape = RoundedCornerShape(8.dp),
                                    color = Slate900,
                                    border = BorderStroke(1.dp, Slate700)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(text = item.itemName, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Slate200)
                                            Text(text = item.ripOffWarning, fontSize = 10.sp, color = SurgeWarningOrange)
                                            Text(text = "Say: '" + item.phoneticKannada + "'", fontSize = 10.sp, color = MonsoonBlue)
                                        }
                                        Column(horizontalAlignment = Alignment.End) {
                                            Text(text = item.fairPriceInr, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = AutoGreen)
                                            Text(text = item.approxUsd, fontSize = 10.sp, color = Slate400)
                                            IconButton(
                                                onClick = { viewModel.playRawKannada(item.survivalKannada, "bench_" + item.id) },
                                                modifier = Modifier.size(28.dp)
                                            ) {
                                                Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = "Play", tint = AutoYellow, modifier = Modifier.size(16.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Search Bar & Clear
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.setSearchQuery(it) },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "Search Hampi, Jog Falls, Kudremukh, Netrani, Udupi...",
                            color = Slate400,
                            fontSize = 13.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = AutoYellow
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Clear",
                                    tint = Slate400
                                )
                            }
                        }
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Slate800,
                        unfocusedContainerColor = Slate800,
                        focusedBorderColor = AutoYellow,
                        unfocusedBorderColor = Slate700,
                        focusedTextColor = Slate200,
                        unfocusedTextColor = Slate200
                    ),
                    singleLine = true
                )
            }

            // Category Filter Horizontal Scroll
            item {
                val scrollState = rememberScrollState()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(scrollState),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // All Pill
                    FilterChip(
                        selected = selectedCategory == null,
                        onClick = { viewModel.selectCategory(null) },
                        label = { Text("🌟 All Categories") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AutoYellow,
                            selectedLabelColor = Slate900,
                            containerColor = Slate800,
                            labelColor = Slate400
                        ),
                        border = BorderStroke(1.dp, if (selectedCategory == null) AutoYellow else Slate700)
                    )

                    // Categories
                    TourismCategory.values().forEach { cat ->
                        val isSelected = selectedCategory == cat
                        val icon = getCategoryIcon(cat)
                        FilterChip(
                            selected = isSelected,
                            onClick = { viewModel.selectCategory(if (isSelected) null else cat) },
                            leadingIcon = {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = if (isSelected) Slate900 else AutoYellow
                                )
                            },
                            label = { Text(cat.label) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = AutoYellow,
                                selectedLabelColor = Slate900,
                                containerColor = Slate800,
                                labelColor = Slate400
                            ),
                            border = BorderStroke(1.dp, if (isSelected) AutoYellow else Slate700)
                        )
                    }
                }
            }

            // Active Results Count Banner
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Showing ${filteredSpots.size} destinations in ${selectedRegion.label}",
                        fontSize = 12.sp,
                        color = Slate400
                    )
                    Text(
                        text = selectedRegion.districtsDescription,
                        fontSize = 11.sp,
                        color = AutoYellow,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Destination Cards List
            if (filteredSpots.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "🔍", fontSize = 40.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "No destinations found",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate200
                        )
                        Text(
                            text = "Try switching region or searching another keyword",
                            fontSize = 12.sp,
                            color = Slate400
                        )
                    }
                }
            } else {
                items(filteredSpots, key = { it.id }) { spot ->
                    DestinationCard(
                        spot = spot,
                        isForeignerMode = isForeignerMode,
                        isSpeaking = isSpeaking && lastSpokenSpotId == spot.id,
                        onPlayAudio = { viewModel.playSpotKannadaPhrase(spot) },
                        onStopAudio = { viewModel.stopAudio() },
                        onViewDetails = { viewModel.selectSpotForDetails(spot) },
                        onOpenBookingUrl = { openBrowserUrl(context, spot.bookingInfo.portalUrl) }
                    )
                }
            }

            // Bottom Spacing
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun DestinationCard(
    spot: KarnatakaSpot,
    isForeignerMode: Boolean,
    isSpeaking: Boolean,
    onPlayAudio: () -> Unit,
    onStopAudio: () -> Unit,
    onViewDetails: () -> Unit,
    onOpenBookingUrl: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onViewDetails() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        border = BorderStroke(1.dp, Slate700)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row: Category Badge + Region Badge + District
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = getCategoryColor(spot.category).copy(alpha = 0.2f),
                        border = BorderStroke(1.dp, getCategoryColor(spot.category))
                    ) {
                        Text(
                            text = spot.category.label,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = getCategoryColor(spot.category),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Slate700.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = spot.region.iconTag + " " + spot.region.label,
                            fontSize = 10.sp,
                            color = Slate200,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }
                }

                Text(
                    text = spot.district,
                    fontSize = 11.sp,
                    color = Slate400,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Spot Title & Kannada Lipi
            Text(
                text = spot.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Slate200
            )
            Text(
                text = spot.kannadaName,
                fontSize = 13.sp,
                color = AutoYellow,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = spot.tagline,
                fontSize = 12.sp,
                color = Slate400,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Transit Hub Indicator
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Slate900.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Train,
                    contentDescription = null,
                    tint = MonsoonBlue,
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = spot.nearestTransitHub,
                    fontSize = 11.sp,
                    color = MonsoonBlue,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Dual Pricing Pill / Box
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = Slate900.copy(alpha = 0.9f),
                border = BorderStroke(1.dp, Slate700)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (isForeignerMode) "🌍 Foreign Tourist Tariff:" else "🇮🇳 Domestic Entry:",
                            fontSize = 10.sp,
                            color = Slate400,
                            fontWeight = FontWeight.SemiBold
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (isForeignerMode) {
                                    if (spot.foreignerPricing.foreignerFeeInr == 0) "Free Entry"
                                    else "${spot.foreignerPricing.approxUsdEquivalent} (₹${spot.foreignerPricing.foreignerFeeInr})"
                                } else {
                                    if (spot.foreignerPricing.domesticFeeInr == 0) "Free Entry"
                                    else "₹${spot.foreignerPricing.domesticFeeInr}"
                                },
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (spot.foreignerPricing.foreignerFeeInr == 0) AutoGreen else AutoYellow
                            )
                        }
                    }

                    // Portal Badge
                    val isAranya = spot.bookingInfo.badgeTag.contains("ARANYA")
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (isAranya) AutoGreenDark.copy(alpha = 0.3f) else MetroPurple.copy(alpha = 0.2f),
                        border = BorderStroke(
                            1.dp,
                            if (isAranya) AutoGreen else MetroPurple
                        )
                    ) {
                        Text(
                            text = spot.bookingInfo.badgeTag,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isAranya) AutoGreen else MetroPurple,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Quick Audio Phrase Box
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = Slate800,
                border = BorderStroke(1.dp, Slate700.copy(alpha = 0.6f))
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "🗣️ Local Kannada to Ask:",
                            fontSize = 10.sp,
                            color = AutoYellow,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = """ + spot.survivalKannadaPhrase + """,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Slate200,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = spot.survivalKannadaMeaning,
                            fontSize = 11.sp,
                            color = Slate400,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    IconButton(
                        onClick = {
                            if (isSpeaking) onStopAudio() else onPlayAudio()
                        },
                        modifier = Modifier
                            .size(36.dp)
                            .background(if (isSpeaking) FastMeterAlert else AutoYellow, CircleShape)
                    ) {
                        Icon(
                            imageVector = if (isSpeaking) Icons.Default.Stop else Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = "Play Speech",
                            tint = Slate900,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action Buttons: Official Booking + Full Details
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val isAranya = spot.bookingInfo.badgeTag.contains("ARANYA")
                Button(
                    onClick = { onOpenBookingUrl() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isAranya) AutoGreenDark else Slate700
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.OpenInBrowser,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Slate200
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isAranya) "Aranya Booking" else "Official Portal",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate200
                    )
                }

                OutlinedButton(
                    onClick = { onViewDetails() },
                    border = BorderStroke(1.dp, AutoYellow),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = AutoYellow
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Details & Rules",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = AutoYellow
                    )
                }
            }
        }
    }
}

@Composable
fun DestinationDetailDialog(
    spot: KarnatakaSpot,
    isForeignerMode: Boolean,
    isSpeaking: Boolean,
    onPlayAudio: () -> Unit,
    onStopAudio: () -> Unit,
    onDismiss: () -> Unit,
    onOpenBookingUrl: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            color = Slate900,
            border = BorderStroke(1.dp, Slate700)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp)
            ) {
                // Top Close Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = getCategoryColor(spot.category).copy(alpha = 0.2f),
                            border = BorderStroke(1.dp, getCategoryColor(spot.category))
                        ) {
                            Text(
                                text = spot.category.label,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = getCategoryColor(spot.category),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "${spot.region.iconTag} ${spot.region.label}",
                            fontSize = 12.sp,
                            color = Slate400
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .size(32.dp)
                            .background(Slate800, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Slate200,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    item {
                        Text(
                            text = spot.name,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate200
                        )
                        Text(
                            text = spot.kannadaName,
                            fontSize = 15.sp,
                            color = AutoYellow,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "District: ${spot.district} | ${spot.nearestTransitHub}",
                            fontSize = 11.sp,
                            color = MonsoonBlue
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = spot.overview,
                            fontSize = 13.sp,
                            color = Slate400,
                            lineHeight = 18.sp
                        )
                    }

                    // Key Highlights
                    item {
                        DetailSectionCard(title = "✨ Top Highlights") {
                            spot.keyHighlights.forEach { highlight ->
                                Row(
                                    modifier = Modifier.padding(vertical = 3.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text(text = "•", color = AutoYellow, fontSize = 16.sp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = highlight,
                                        fontSize = 12.sp,
                                        color = Slate200
                                    )
                                }
                            }
                        }
                    }

                    // Pricing & Foreigner Guidance
                    item {
                        DetailSectionCard(title = "💰 Official Ticket & Foreigner Pricing") {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = "Domestic Citizen:",
                                        fontSize = 11.sp,
                                        color = Slate400
                                    )
                                    Text(
                                        text = if (spot.foreignerPricing.domesticFeeInr == 0) "Free" else "₹${spot.foreignerPricing.domesticFeeInr}",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Slate200
                                    )
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    Text(
                                        text = "Foreign National:",
                                        fontSize = 11.sp,
                                        color = Slate400
                                    )
                                    Text(
                                        text = if (spot.foreignerPricing.foreignerFeeInr == 0) "Free" else "${spot.foreignerPricing.approxUsdEquivalent} (₹${spot.foreignerPricing.foreignerFeeInr})",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AutoYellow
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Includes: ${spot.foreignerPricing.feeIncludes}",
                                fontSize = 11.sp,
                                color = MonsoonBlue
                            )
                        }
                    }

                    // Timings & Best Season
                    item {
                        DetailSectionCard(title = "🕒 Best Season & Operating Timings") {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Best Season: ",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AutoYellow
                                )
                                Text(
                                    text = spot.bestSeason,
                                    fontSize = 12.sp,
                                    color = Slate200
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Timings: ",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AutoYellow
                                )
                                Text(
                                    text = spot.timings,
                                    fontSize = 12.sp,
                                    color = Slate200
                                )
                            }
                        }
                    }

                    // Dress Code & Cultural Etiquette
                    item {
                        DetailSectionCard(title = "👔 Dress Code & Cultural Etiquette") {
                            Text(
                                text = spot.foreignerPricing.dressCodeRules,
                                fontSize = 12.sp,
                                color = Slate200
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Photography: ${spot.foreignerPricing.photographyPolicy}",
                                fontSize = 11.sp,
                                color = Slate400
                            )
                        }
                    }

                    // Scam Alert & Tourist Safeguard
                    item {
                        DetailSectionCard(
                            title = "⚠️ Scam Alert & Precautions",
                            borderColor = FastMeterAlert.copy(alpha = 0.5f)
                        ) {
                            Text(
                                text = spot.foreignerPricing.scamAlertNotes,
                                fontSize = 12.sp,
                                color = SurgeWarningOrange
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Payment / Network: ${spot.foreignerPricing.cashVsDigitalNotes}",
                                fontSize = 11.sp,
                                color = Slate400
                            )
                        }
                    }

                    // How to Reach & Transit
                    item {
                        DetailSectionCard(title = "🚌 How to Reach & Regional Transit") {
                            Text(
                                text = spot.howToReachTransit,
                                fontSize = 12.sp,
                                color = Slate200
                            )
                        }
                    }

                    // Survival Audio Phrase
                    item {
                        DetailSectionCard(
                            title = "🗣️ On-Location Kannada Audio Phrase",
                            borderColor = AutoYellow.copy(alpha = 0.5f)
                        ) {
                            Text(
                                text = spot.survivalKannadaLipi,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = AutoYellow
                            )
                            Text(
                                text = spot.survivalKannadaPhrase,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Slate200
                            )
                            Text(
                                text = "Meaning: ${spot.survivalKannadaMeaning}",
                                fontSize = 11.sp,
                                color = Slate400
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = {
                                    if (isSpeaking) onStopAudio() else onPlayAudio()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isSpeaking) FastMeterAlert else AutoYellow
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = if (isSpeaking) Icons.Default.Stop else Icons.AutoMirrored.Filled.VolumeUp,
                                    contentDescription = null,
                                    tint = Slate900,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (isSpeaking) "Stop Audio" else "Speak Kannada to Driver / Counter",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Slate900
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Bottom Action: Open Booking Portal
                val isAranya = spot.bookingInfo.badgeTag.contains("ARANYA")
                Button(
                    onClick = onOpenBookingUrl,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isAranya) AutoGreenDark else MonsoonBlue
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.OpenInBrowser,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Slate200
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Book on ${spot.bookingInfo.portalName}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate200
                    )
                }
            }
        }
    }
}

@Composable
fun DetailSectionCard(
    title: String,
    borderColor: Color = Slate700,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Slate800.copy(alpha = 0.7f),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = AutoYellow
            )
            Spacer(modifier = Modifier.height(6.dp))
            content()
        }
    }
}

fun getCategoryIcon(cat: TourismCategory): ImageVector {
    return when (cat) {
        TourismCategory.TEMPLES_HERITAGE -> Icons.Default.TempleHindu
        TourismCategory.ADVENTURE_SPORTS -> Icons.Default.Kayaking
        TourismCategory.TREKKING_TRAILS -> Icons.Default.Hiking
        TourismCategory.BEACHES -> Icons.Default.BeachAccess
        TourismCategory.ISLANDS -> Icons.Default.Explore
        TourismCategory.WATERFALLS_NATURE -> Icons.Default.WaterDrop
        TourismCategory.WILDLIFE_SAFARI -> Icons.Default.Pets
    }
}

fun getCategoryColor(cat: TourismCategory): Color {
    return when (cat) {
        TourismCategory.TEMPLES_HERITAGE -> AutoYellow
        TourismCategory.ADVENTURE_SPORTS -> SurgeWarningOrange
        TourismCategory.TREKKING_TRAILS -> AutoGreen
        TourismCategory.BEACHES -> MonsoonBlue
        TourismCategory.ISLANDS -> MetroPurple
        TourismCategory.WATERFALLS_NATURE -> Color(0xFF38BDF8)
        TourismCategory.WILDLIFE_SAFARI -> Color(0xFFF59E0B)
    }
}

fun openBrowserUrl(context: Context, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Unable to open browser: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}
