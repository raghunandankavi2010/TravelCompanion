package com.namma.travelcompanion.presentation.favorites

import androidx.lifecycle.ViewModel
import com.namma.travelcompanion.data.repository.FavoritesManager
import com.namma.travelcompanion.feature.dining.data.repository.KarnatakaVegFoodRepository
import com.namma.travelcompanion.feature.dining.domain.model.VegRestaurant
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaSpot
import com.namma.travelcompanion.feature.tourism.domain.repository.KarnatakaTourismRepository
import kotlinx.coroutines.flow.StateFlow

class FavoritesViewModel(private val favoritesManager: FavoritesManager) : ViewModel() {

    val favoriteSpotIds: StateFlow<Set<String>> = favoritesManager.favoriteSpotIds
    val favoriteRestaurantNames: StateFlow<Set<String>> = favoritesManager.favoriteRestaurantNames

    val allSpots: List<KarnatakaSpot> = KarnatakaTourismRepository.spots
    val allRestaurants: List<VegRestaurant> = KarnatakaVegFoodRepository.restaurants

    fun toggleSpotFavorite(spotId: String) {
        favoritesManager.toggleSpotFavorite(spotId)
    }

    fun toggleRestaurantFavorite(name: String) {
        favoritesManager.toggleRestaurantFavorite(name)
    }
}
