package com.namma.travelcompanion.presentation.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namma.travelcompanion.data.repository.OpenPlacesRepository
import com.namma.travelcompanion.domain.model.District
import com.namma.travelcompanion.domain.model.KarnatakaDistricts
import com.namma.travelcompanion.domain.model.OpenPlace
import com.namma.travelcompanion.domain.model.PlaceCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DistrictUiState(
    val district: District?,
    val isLoading: Boolean = true,
    val totalPlaces: Int = 0,
    /** Every category present in the district, with its place count, for the chips. */
    val categoryCounts: List<Pair<PlaceCategory, Int>> = emptyList(),
    val selectedCategory: PlaceCategory? = null,
    /** What to list: all categories as sections, or just the selected one. */
    val sections: List<Pair<PlaceCategory, List<OpenPlace>>> = emptyList()
)

class DistrictViewModel(private val repository: OpenPlacesRepository, districtId: String) : ViewModel() {

    private val _state = MutableStateFlow(DistrictUiState(KarnatakaDistricts.byId(districtId)))
    val state: StateFlow<DistrictUiState> = _state.asStateFlow()

    private var allSections: List<Pair<PlaceCategory, List<OpenPlace>>> = emptyList()

    init {
        viewModelScope.launch {
            val places = repository.places().filter { it.districtId == districtId }
            // Famous places (UNESCO, then ones with photos) first within each section.
            allSections = PlaceCategory.group(places.sortedWith(compareBy({ !it.worldHeritage }, { !it.hasImage }, { it.name })))
            _state.update {
                it.copy(
                    isLoading = false,
                    totalPlaces = allSections.sumOf { (_, list) -> list.size },
                    categoryCounts = allSections.map { (category, list) -> category to list.size },
                    sections = allSections
                )
            }
        }
    }

    fun selectCategory(category: PlaceCategory?) = _state.update {
        it.copy(selectedCategory = category, sections = if (category == null) allSections else allSections.filter { s -> s.first == category })
    }
}
