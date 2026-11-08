package com.namma.travelcompanion.feature.tourism.ui

import android.content.Context
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
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
import com.namma.travelcompanion.core.theme.SurgeWarningOrange
import com.namma.travelcompanion.core.util.openBrowserUrl
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaRegion
import com.namma.travelcompanion.feature.tourism.domain.model.TourismCategory
import com.namma.travelcompanion.feature.tourism.domain.model.TouristGuidanceData
import com.namma.travelcompanion.presentation.components.GlassCard

@Composable
fun KarnatakaExplorerScreen(
    viewModel: KarnatakaExplorerViewModel,
    onNavigateToPhrasebook: () -> Unit,
    onNavigateToProStore: () -> Unit,
    onNavigateToBusLens: () -> Unit = {}
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
                                text = "Travel Companion",
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate200
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "🧭", fontSize = 22.sp)
                        }
                        Text(
                            text = "Karnataka Explorer • Temples, Wildlife & Nature",
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

            // Quick AI Bus & Signboard Lens Action Card
            item {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Slate800,
                    border = BorderStroke(1.dp, AutoYellow.copy(alpha = 0.6f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNavigateToBusLens() }
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = AutoYellow.copy(alpha = 0.2f),
                                modifier = Modifier.size(42.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(text = "🚌", fontSize = 20.sp)
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Bus Board & Signboard Lens",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Slate200
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = AutoGreen.copy(alpha = 0.25f)
                                    ) {
                                        Text(
                                            text = "ON-DEVICE AI",
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = AutoGreen,
                                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "Scan Kannada bus boards & temple signboards",
                                    fontSize = 11.sp,
                                    color = Slate400
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = "Open Lens",
                            tint = AutoYellow,
                            modifier = Modifier.size(20.dp)
                        )
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
