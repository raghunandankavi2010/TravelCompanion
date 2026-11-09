package com.namma.travelcompanion.presentation.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namma.travelcompanion.network.api.LiveTravelScraperService
import com.namma.travelcompanion.data.repository.OpenPlacesRepository
import com.namma.travelcompanion.data.repository.VisitEssentialsRepository
import com.namma.travelcompanion.domain.model.ChannelVideo
import com.namma.travelcompanion.domain.model.ChannelVideoMatcher
import com.namma.travelcompanion.domain.model.District
import com.namma.travelcompanion.domain.model.KarnatakaDistricts
import com.namma.travelcompanion.domain.model.OpenPlace
import com.namma.travelcompanion.domain.model.PlaceCategory
import com.namma.travelcompanion.domain.model.VisitEssentials
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PlaceDetailUiState(
    val place: OpenPlace? = null,
    val district: District? = null,
    val category: PlaceCategory? = null,
    val videos: List<ChannelVideo> = emptyList(),
    val essentials: VisitEssentials? = null,
    /** Wikipedia intro; null while loading or when offline (the Wikidata description is shown instead). */
    val wikipediaSummary: String? = null,
    val isLoadingSummary: Boolean = true
)

class PlaceDetailViewModel(
    private val repository: OpenPlacesRepository,
    private val essentialsRepository: VisitEssentialsRepository,
    placeId: String
) : ViewModel() {

    private val _state = MutableStateFlow(PlaceDetailUiState())
    val state: StateFlow<PlaceDetailUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val essentials = essentialsRepository.forPlace(placeId)
            _state.update { it.copy(essentials = essentials) }
        }
        viewModelScope.launch {
            val place = repository.places().firstOrNull { it.id == placeId } ?: return@launch
            _state.update {
                it.copy(
                    place = place,
                    district = KarnatakaDistricts.byId(place.districtId),
                    category = PlaceCategory.of(place),
                    videos = ChannelVideoMatcher.videosFor(place, repository.channelVideos())
                )
            }
            if (!place.hasWikipedia) {
                _state.update { it.copy(isLoadingSummary = false) }
                return@launch
            }
            val info = LiveTravelScraperService.scrapeLiveTravelInfo(place.wikipediaTitle)
            _state.update {
                it.copy(
                    wikipediaSummary = info.extract.takeIf { info.isLiveScraped && it.isNotBlank() },
                    isLoadingSummary = false
                )
            }
        }
    }
}
