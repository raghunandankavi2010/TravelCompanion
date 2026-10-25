package com.nammaauto.ai.presentation.tourism

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nammaauto.ai.NammaAutoApplication
import com.nammaauto.ai.data.speech.KannadaTtsEngine
import com.nammaauto.ai.domain.model.CityTransportTariff
import com.nammaauto.ai.domain.model.KarnatakaRegion
import com.nammaauto.ai.domain.model.KarnatakaSpot
import com.nammaauto.ai.domain.model.RegionalDialectInfo
import com.nammaauto.ai.domain.model.TourismCategory
import com.nammaauto.ai.domain.repository.KarnatakaTourismRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class KarnatakaExplorerViewModel(application: Application) : AndroidViewModel(application) {

    private val ttsEngine = KannadaTtsEngine(application)
    private val billingManager = (application as NammaAutoApplication).billingManager

    val isSpeaking = ttsEngine.isSpeaking

    private val _selectedRegion = MutableStateFlow<KarnatakaRegion>(KarnatakaRegion.ALL)
    val selectedRegion: StateFlow<KarnatakaRegion> = _selectedRegion.asStateFlow()

    private val _selectedCategory = MutableStateFlow<TourismCategory?>(null)
    val selectedCategory: StateFlow<TourismCategory?> = _selectedCategory.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isForeignerMode = MutableStateFlow(true)
    val isForeignerMode: StateFlow<Boolean> = _isForeignerMode.asStateFlow()

    private val _selectedSpotForDetails = MutableStateFlow<KarnatakaSpot?>(null)
    val selectedSpotForDetails: StateFlow<KarnatakaSpot?> = _selectedSpotForDetails.asStateFlow()

    private val _lastSpokenSpotId = MutableStateFlow<String?>(null)
    val lastSpokenSpotId: StateFlow<String?> = _lastSpokenSpotId.asStateFlow()

    val regionalDialects: List<RegionalDialectInfo> = KarnatakaTourismRepository.regionalDialects
    val cityTariffs: List<CityTransportTariff> = KarnatakaTourismRepository.cityTariffs

    // Filtered spots derived from Region, Category, and Search Query
    val filteredSpots: StateFlow<List<KarnatakaSpot>> = combine(
        _selectedRegion,
        _selectedCategory,
        _searchQuery
    ) { region, category, query ->
        KarnatakaTourismRepository.spots.filter { spot ->
            val matchesRegion = region == KarnatakaRegion.ALL || spot.region == region
            val matchesCategory = category == null || spot.category == category
            val matchesQuery = query.isBlank() ||
                spot.name.contains(query, ignoreCase = true) ||
                spot.district.contains(query, ignoreCase = true) ||
                spot.tagline.contains(query, ignoreCase = true) ||
                spot.overview.contains(query, ignoreCase = true) ||
                spot.nearestTransitHub.contains(query, ignoreCase = true) ||
                spot.keyHighlights.any { it.contains(query, ignoreCase = true) }

            matchesRegion && matchesCategory && matchesQuery
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), KarnatakaTourismRepository.spots)

    fun selectRegion(region: KarnatakaRegion) {
        _selectedRegion.value = region
    }

    fun selectCategory(category: TourismCategory?) {
        _selectedCategory.value = category
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleForeignerMode() {
        _isForeignerMode.value = !_isForeignerMode.value
    }

    fun selectSpotForDetails(spot: KarnatakaSpot?) {
        _selectedSpotForDetails.value = spot
    }

    fun playSpotKannadaPhrase(spot: KarnatakaSpot): Boolean {
        _lastSpokenSpotId.value = spot.id
        ttsEngine.speak(spot.survivalKannadaLipi, "tourist_phrase_${spot.id}")
        return true
    }

    fun playRawKannada(kannadaText: String, utteranceId: String = "custom_raw"): Boolean {
        ttsEngine.speak(kannadaText, utteranceId)
        return true
    }

    fun stopAudio() {
        ttsEngine.stop()
    }

    override fun onCleared() {
        super.onCleared()
        ttsEngine.release()
    }
}
