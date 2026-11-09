package com.namma.travelcompanion.presentation.tourism

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.ui.components.openUrl
import com.namma.travelcompanion.presentation.tourism.components.BusLensCard
import com.namma.travelcompanion.presentation.tourism.components.CategoryFilterRow
import com.namma.travelcompanion.presentation.tourism.components.DestinationCard
import com.namma.travelcompanion.presentation.tourism.components.DestinationDetailDialog
import com.namma.travelcompanion.presentation.tourism.components.DialectsPanel
import com.namma.travelcompanion.presentation.tourism.components.EmptyDestinations
import com.namma.travelcompanion.presentation.tourism.components.ExplorerHeader
import com.namma.travelcompanion.presentation.tourism.components.ExplorerSearchBar
import com.namma.travelcompanion.presentation.tourism.components.InfoHubToggles
import com.namma.travelcompanion.presentation.tourism.components.RegionSelector
import com.namma.travelcompanion.presentation.tourism.components.ResultsBanner
import com.namma.travelcompanion.presentation.tourism.components.ScamBusterPanel
import com.namma.travelcompanion.presentation.tourism.components.TransportTariffsPanel

@Composable
fun KarnatakaExplorerScreen(
    viewModel: KarnatakaExplorerViewModel,
    onNavigateToPhrasebook: () -> Unit,
    onNavigateToProStore: () -> Unit,
    onNavigateToBusLens: () -> Unit = {},
    onReadSign: () -> Unit = onNavigateToBusLens
) {
    val context = LocalContext.current
    val selectedRegion by viewModel.selectedRegion.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isForeignerMode by viewModel.isForeignerMode.collectAsState()
    val filteredSpots by viewModel.filteredSpots.collectAsState()
    val selectedSpotForDetails by viewModel.selectedSpotForDetails.collectAsState()
    val selectedSpotEssentials by viewModel.selectedSpotEssentials.collectAsState()
    val isSpeaking by viewModel.isSpeaking.collectAsState()
    val lastSpokenSpotId by viewModel.lastSpokenSpotId.collectAsState()

    var showScamBusterTab by remember { mutableStateOf(false) }
    var showStatewideTransportTab by remember { mutableStateOf(false) }
    var showRegionalDialectTab by remember { mutableStateOf(false) }

    // Detail Dialog
    selectedSpotForDetails?.let { spot ->
        DestinationDetailDialog(
            spot = spot,
            essentials = selectedSpotEssentials,
            onReadSign = onReadSign,
            isForeignerMode = isForeignerMode,
            isSpeaking = isSpeaking && lastSpokenSpotId == spot.id,
            onPlayAudio = { viewModel.playSpotKannadaPhrase(spot) },
            onStopAudio = { viewModel.stopAudio() },
            onDismiss = { viewModel.selectSpotForDetails(null) },
            onOpenBookingUrl = { openUrl(context, spot.bookingInfo.portalUrl) }
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
            item {
                ExplorerHeader(
                    isForeignerMode = isForeignerMode,
                    onToggleForeignerMode = { viewModel.toggleForeignerMode() }
                )
            }

            item { BusLensCard(onClick = { onNavigateToBusLens() }) }

            item {
                RegionSelector(
                    selectedRegion = selectedRegion,
                    onSelectRegion = { viewModel.selectRegion(it) }
                )
            }

            item {
                InfoHubToggles(
                    showTransport = showStatewideTransportTab,
                    showDialects = showRegionalDialectTab,
                    showScamBuster = showScamBusterTab,
                    onToggleTransport = { showStatewideTransportTab = !showStatewideTransportTab },
                    onToggleDialects = { showRegionalDialectTab = !showRegionalDialectTab },
                    onToggleScamBuster = { showScamBusterTab = !showScamBusterTab }
                )
            }

            item {
                TransportTariffsPanel(
                    visible = showStatewideTransportTab,
                    tariffs = viewModel.cityTariffs,
                    onClose = { showStatewideTransportTab = false },
                    onOpenUrl = { openUrl(context, it) }
                )
            }

            item {
                DialectsPanel(
                    visible = showRegionalDialectTab,
                    dialects = viewModel.regionalDialects,
                    onClose = { showRegionalDialectTab = false }
                )
            }

            item {
                ScamBusterPanel(
                    visible = showScamBusterTab,
                    onClose = { showScamBusterTab = false },
                    onPlayKannada = { text, id -> viewModel.playRawKannada(text, id) }
                )
            }

            item {
                ExplorerSearchBar(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { viewModel.setSearchQuery(it) }
                )
            }

            item {
                CategoryFilterRow(
                    selectedCategory = selectedCategory,
                    onSelectCategory = { viewModel.selectCategory(it) }
                )
            }

            item {
                ResultsBanner(
                    resultCount = filteredSpots.size,
                    selectedRegion = selectedRegion
                )
            }

            if (filteredSpots.isEmpty()) {
                item { EmptyDestinations() }
            } else {
                items(filteredSpots, key = { it.id }) { spot ->
                    DestinationCard(
                        spot = spot,
                        isForeignerMode = isForeignerMode,
                        isSpeaking = isSpeaking && lastSpokenSpotId == spot.id,
                        onPlayAudio = { viewModel.playSpotKannadaPhrase(spot) },
                        onStopAudio = { viewModel.stopAudio() },
                        onViewDetails = { viewModel.selectSpotForDetails(spot) },
                        onOpenBookingUrl = { openUrl(context, spot.bookingInfo.portalUrl) }
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
