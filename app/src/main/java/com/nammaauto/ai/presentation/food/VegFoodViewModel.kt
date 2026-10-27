package com.nammaauto.ai.presentation.food

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nammaauto.ai.data.speech.KannadaTtsEngine
import com.nammaauto.ai.domain.model.TempleAnnadanaGuidance
import com.nammaauto.ai.domain.model.VegCategory
import com.nammaauto.ai.domain.model.VegRestaurant
import com.nammaauto.ai.domain.repository.KarnatakaVegFoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class VegFoodViewModel(application: Application) : AndroidViewModel(application) {

    private val ttsEngine = KannadaTtsEngine(application)
    val isSpeaking: StateFlow<Boolean> = ttsEngine.isSpeaking

    private val _selectedCategory = MutableStateFlow<VegCategory?>(null)
    val selectedCategory: StateFlow<VegCategory?> = _selectedCategory.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedAnnadana = MutableStateFlow<TempleAnnadanaGuidance?>(null)
    val selectedAnnadana: StateFlow<TempleAnnadanaGuidance?> = _selectedAnnadana.asStateFlow()

    val annadanaGuidelines: List<TempleAnnadanaGuidance> = KarnatakaVegFoodRepository.templeAnnadanaGuidelines

    val filteredRestaurants: StateFlow<List<VegRestaurant>> = combine(
        _selectedCategory,
        _searchQuery
    ) { category, query ->
        val list = if (category == null) {
            KarnatakaVegFoodRepository.restaurants
        } else {
            KarnatakaVegFoodRepository.getRestaurantsByCategory(category)
        }

        if (query.isBlank()) {
            list
        } else {
            val q = query.trim().lowercase()
            list.filter {
                it.name.lowercase().contains(q) ||
                it.district.lowercase().contains(q) ||
                it.highwayOrLocation.lowercase().contains(q) ||
                it.signatureDishes.any { dish -> dish.lowercase().contains(q) } ||
                it.proTip.lowercase().contains(q)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), KarnatakaVegFoodRepository.restaurants)

    fun selectCategory(category: VegCategory?) {
        _selectedCategory.value = category
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectAnnadanaGuidance(guidance: TempleAnnadanaGuidance?) {
        _selectedAnnadana.value = guidance
    }

    fun playKannadaOrderingPhrase(text: String) {
        ttsEngine.speak(text, "food_phrase_${System.currentTimeMillis()}")
    }

    fun stopAudio() {
        ttsEngine.stop()
    }

    override fun onCleared() {
        super.onCleared()
        ttsEngine.release()
    }
}
