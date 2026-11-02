package com.nammaauto.ai.presentation.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nammaauto.ai.NammaAutoApplication
import com.nammaauto.ai.domain.model.KarnatakaSpot
import com.nammaauto.ai.domain.model.VegRestaurant
import com.nammaauto.ai.domain.repository.KarnatakaTourismRepository
import com.nammaauto.ai.domain.repository.KarnatakaVegFoodRepository
import kotlinx.coroutines.flow.StateFlow

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val favoritesManager = (application as NammaAutoApplication).favoritesManager

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
