package com.namma.travelcompanion.feature.tourism.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namma.travelcompanion.data.billing.BillingManager
import com.namma.travelcompanion.data.speech.KannadaTtsEngine
import com.namma.travelcompanion.feature.tourism.data.api.LiveOfficialTariff
import com.namma.travelcompanion.feature.tourism.data.api.LiveTourismPriceService
import com.namma.travelcompanion.feature.tourism.domain.model.AiBudgetCalculationResult
import com.namma.travelcompanion.feature.tourism.domain.model.AiQnaItem
import com.namma.travelcompanion.feature.tourism.domain.model.CityTransportTariff
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaAiTripIntelligence
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaRegion
import com.namma.travelcompanion.feature.tourism.domain.model.KarnatakaSpot
import com.namma.travelcompanion.feature.tourism.domain.model.RegionalDialectInfo
import com.namma.travelcompanion.feature.tourism.domain.model.TourismCategory
import com.namma.travelcompanion.feature.tourism.domain.repository.KarnatakaTourismRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class KarnatakaExplorerViewModel(
    private val ttsEngine: KannadaTtsEngine,
    private val billingManager: BillingManager
) : ViewModel() {

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

    // Live Official Tariffs from Online Service
    val isSyncingLivePrices: StateFlow<Boolean> = LiveTourismPriceService.isSyncing
    val lastSyncTimestamp: StateFlow<String> = LiveTourismPriceService.lastSyncTimestamp
    val liveTariffsList: List<LiveOfficialTariff> = LiveTourismPriceService.liveTariffs

    // AI Trip Budget State
    private val _selectedBudgetSpotIds = MutableStateFlow<Set<String>>(
        setOf("mysore_palace", "kabini_safari", "bandipur_safari", "kudremukh_trek")
    )
    val selectedBudgetSpotIds: StateFlow<Set<String>> = _selectedBudgetSpotIds.asStateFlow()

    private val _adultCount = MutableStateFlow(2)
    val adultCount: StateFlow<Int> = _adultCount.asStateFlow()

    private val _childCount = MutableStateFlow(0)
    val childCount: StateFlow<Int> = _childCount.asStateFlow()

    private val _hasDslrCamera = MutableStateFlow(false)
    val hasDslrCamera: StateFlow<Boolean> = _hasDslrCamera.asStateFlow()

    private val _budgetCalculation = MutableStateFlow<AiBudgetCalculationResult?>(null)
    val budgetCalculation: StateFlow<AiBudgetCalculationResult?> = _budgetCalculation.asStateFlow()

    // AI Tourist Q&A State
    private val _activeAiQna = MutableStateFlow<AiQnaItem?>(KarnatakaAiTripIntelligence.commonTouristQueries[0])
    val activeAiQna: StateFlow<AiQnaItem?> = _activeAiQna.asStateFlow()

    val aiQuickQuestions: List<AiQnaItem> = KarnatakaAiTripIntelligence.commonTouristQueries

    init {
        recomputeBudget()
    }

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
        recomputeBudget()
    }

    fun selectSpotForDetails(spot: KarnatakaSpot?) {
        _selectedSpotForDetails.value = spot
    }

    fun refreshLivePrices() {
        viewModelScope.launch {
            LiveTourismPriceService.refreshLiveTariffs()
            recomputeBudget()
        }
    }

    fun getLiveTariffForSpot(spotId: String): LiveOfficialTariff? {
        return LiveTourismPriceService.getTariffForSpot(spotId)
    }

    fun toggleBudgetSpot(spotId: String) {
        val current = _selectedBudgetSpotIds.value.toMutableSet()
        if (current.contains(spotId)) {
            current.remove(spotId)
        } else {
            current.add(spotId)
        }
        _selectedBudgetSpotIds.value = current
        recomputeBudget()
    }

    fun setAdultCount(count: Int) {
        if (count in 1..20) {
            _adultCount.value = count
            recomputeBudget()
        }
    }

    fun setChildCount(count: Int) {
        if (count in 0..15) {
            _childCount.value = count
            recomputeBudget()
        }
    }

    fun toggleDslrCamera() {
        _hasDslrCamera.value = !_hasDslrCamera.value
        recomputeBudget()
    }

    fun askAiAssistant(query: String) {
        val answer = KarnatakaAiTripIntelligence.answerTouristQuery(query)
        _activeAiQna.value = answer
    }

    fun selectAiQuestionItem(item: AiQnaItem) {
        _activeAiQna.value = item
    }

    private fun recomputeBudget() {
        val isForeign = _isForeignerMode.value
        val indianAdults = if (isForeign) 0 else _adultCount.value
        val foreignAdults = if (isForeign) _adultCount.value else 0
        val indianKids = if (isForeign) 0 else _childCount.value
        val foreignKids = if (isForeign) _childCount.value else 0

        _budgetCalculation.value = KarnatakaAiTripIntelligence.calculateTripBudget(
            indianAdults = indianAdults,
            foreignAdults = foreignAdults,
            indianChildren = indianKids,
            foreignChildren = foreignKids,
            selectedSpotIds = _selectedBudgetSpotIds.value.toList(),
            hasDslrCamera = _hasDslrCamera.value
        )
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
