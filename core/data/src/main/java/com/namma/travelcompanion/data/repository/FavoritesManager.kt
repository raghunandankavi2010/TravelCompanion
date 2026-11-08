package com.namma.travelcompanion.data.repository

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoritesManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("namma_travel_favorites", Context.MODE_PRIVATE)

    private val _favoriteSpotIds = MutableStateFlow<Set<String>>(loadSpotIds())
    val favoriteSpotIds: StateFlow<Set<String>> = _favoriteSpotIds.asStateFlow()

    private val _favoriteRestaurantNames = MutableStateFlow<Set<String>>(loadRestaurantNames())
    val favoriteRestaurantNames: StateFlow<Set<String>> = _favoriteRestaurantNames.asStateFlow()

    private fun loadSpotIds(): Set<String> {
        return prefs.getStringSet("favorite_spots", emptySet()) ?: emptySet()
    }

    private fun loadRestaurantNames(): Set<String> {
        return prefs.getStringSet("favorite_restaurants", emptySet()) ?: emptySet()
    }

    fun toggleSpotFavorite(spotId: String) {
        val current = _favoriteSpotIds.value.toMutableSet()
        if (current.contains(spotId)) {
            current.remove(spotId)
        } else {
            current.add(spotId)
        }
        prefs.edit().putStringSet("favorite_spots", current).apply()
        _favoriteSpotIds.value = current
    }

    fun toggleRestaurantFavorite(restaurantName: String) {
        val current = _favoriteRestaurantNames.value.toMutableSet()
        if (current.contains(restaurantName)) {
            current.remove(restaurantName)
        } else {
            current.add(restaurantName)
        }
        prefs.edit().putStringSet("favorite_restaurants", current).apply()
        _favoriteRestaurantNames.value = current
    }

    fun isSpotFavorite(spotId: String): Boolean = _favoriteSpotIds.value.contains(spotId)
    fun isRestaurantFavorite(name: String): Boolean = _favoriteRestaurantNames.value.contains(name)
}
