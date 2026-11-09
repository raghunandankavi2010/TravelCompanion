package com.namma.travelcompanion.presentation.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namma.travelcompanion.data.repository.OpenPlacesRepository
import com.namma.travelcompanion.domain.model.ChannelVideo
import com.namma.travelcompanion.domain.model.District
import com.namma.travelcompanion.domain.model.DistrictShape
import com.namma.travelcompanion.domain.model.KarnatakaDistricts
import com.namma.travelcompanion.domain.model.OpenPlace
import com.namma.travelcompanion.domain.model.PlaceSearch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DistrictsHomeUiState(
    val isLoading: Boolean = true,
    val shapes: List<DistrictShape> = emptyList(),
    val districts: List<Pair<District, Int>> = KarnatakaDistricts.all.map { it to 0 },
    val channelVideos: List<ChannelVideo> = emptyList(),
    val query: String = "",
    /** Places matching [query] across the whole state; empty when the query is blank. */
    val searchResults: List<OpenPlace> = emptyList()
)

class DistrictsHomeViewModel(private val repository: OpenPlacesRepository) : ViewModel() {

    private val _state = MutableStateFlow(DistrictsHomeUiState())
    val state: StateFlow<DistrictsHomeUiState> = _state.asStateFlow()

    private var allPlaces: List<OpenPlace> = emptyList()

    init {
        viewModelScope.launch {
            allPlaces = repository.places()
            val counts = allPlaces.groupingBy { it.districtId }.eachCount()
            _state.update {
                it.copy(
                    isLoading = false,
                    shapes = repository.districtShapes(),
                    districts = KarnatakaDistricts.all.map { d -> d to (counts[d.id] ?: 0) },
                    channelVideos = repository.channelVideos()
                )
            }
        }
    }

    fun setQuery(query: String) = _state.update {
        it.copy(query = query, searchResults = if (query.isBlank()) emptyList() else PlaceSearch.filter(allPlaces, query, null))
    }
}
