package com.namma.travelcompanion.presentation.food

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.ui.components.openMapsSearch
import com.namma.travelcompanion.presentation.food.components.AnnadanaDetailDialog
import com.namma.travelcompanion.presentation.food.components.AnnadanaSpotlightCard
import com.namma.travelcompanion.presentation.food.components.FoodPhrasesBanner
import com.namma.travelcompanion.presentation.food.components.VegCategoryChips
import com.namma.travelcompanion.presentation.food.components.VegFoodHeader
import com.namma.travelcompanion.presentation.food.components.VegRestaurantCard
import com.namma.travelcompanion.presentation.food.components.VegSearchBar

@Composable
fun VegFoodScreen(viewModel: VegFoodViewModel) {
    val context = LocalContext.current
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val restaurants by viewModel.filteredRestaurants.collectAsState()
    val selectedAnnadana by viewModel.selectedAnnadana.collectAsState()
    val isSpeaking by viewModel.isSpeaking.collectAsState()

    var showPhrasesModal by remember { mutableStateOf(false) }

    // Temple Annadana Info Dialog
    selectedAnnadana?.let { annadana ->
        AnnadanaDetailDialog(
            guidance = annadana,
            onDismiss = { viewModel.selectAnnadanaGuidance(null) }
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
                VegFoodHeader(onTogglePhrases = { showPhrasesModal = !showPhrasesModal })
            }

            item {
                FoodPhrasesBanner(
                    visible = showPhrasesModal,
                    onClose = { showPhrasesModal = false },
                    onPlayPhrase = { viewModel.playKannadaOrderingPhrase(it) }
                )
            }

            item {
                AnnadanaSpotlightCard(
                    onClick = { viewModel.selectAnnadanaGuidance(viewModel.annadanaGuidelines[0]) }
                )
            }

            item {
                VegSearchBar(
                    query = searchQuery,
                    onQueryChange = { viewModel.setSearchQuery(it) }
                )
            }

            item {
                VegCategoryChips(
                    selectedCategory = selectedCategory,
                    totalCount = restaurants.size,
                    onSelect = { viewModel.selectCategory(it) }
                )
            }

            // Restaurant Cards
            items(restaurants, key = { it.id }) { restaurant ->
                VegRestaurantCard(
                    restaurant = restaurant,
                    onNavigateToLocation = {
                        openMapsSearch(context, restaurant.mapsSearchQuery)
                    },
                    onAnnadanaClick = {
                        val guidance = viewModel.annadanaGuidelines.firstOrNull { it.templeName.contains(restaurant.district, ignoreCase = true) || restaurant.name.contains(it.templeName, ignoreCase = true) }
                            ?: viewModel.annadanaGuidelines[0]
                        viewModel.selectAnnadanaGuidance(guidance)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
