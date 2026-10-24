package com.nammaauto.ai.presentation.calculator

import androidx.lifecycle.ViewModel
import com.nammaauto.ai.core.util.BangaloreHub
import com.nammaauto.ai.core.util.BangaloreLocations
import com.nammaauto.ai.domain.model.BangaloreTariff
import com.nammaauto.ai.domain.model.CommuteComparison
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FareEstimatorViewModel : ViewModel() {

    val hubs: List<BangaloreHub> = BangaloreLocations.hubs

    private val _fromHub = MutableStateFlow(hubs[0]) // Silk Board
    val fromHub: StateFlow<BangaloreHub> = _fromHub.asStateFlow()

    private val _toHub = MutableStateFlow(hubs[1]) // Koramangala
    val toHub: StateFlow<BangaloreHub> = _toHub.asStateFlow()

    private val _trafficMultiplier = MutableStateFlow(1.0)
    val trafficMultiplier: StateFlow<Double> = _trafficMultiplier.asStateFlow()

    private val _isRaining = MutableStateFlow(false)
    val isRaining: StateFlow<Boolean> = _isRaining.asStateFlow()

    private val _comparison = MutableStateFlow(calculateCurrentComparison())
    val comparison: StateFlow<CommuteComparison> = _comparison.asStateFlow()

    fun setFromHub(hub: BangaloreHub) {
        _fromHub.value = hub
        _comparison.value = calculateCurrentComparison()
    }

    fun setToHub(hub: BangaloreHub) {
        _toHub.value = hub
        _comparison.value = calculateCurrentComparison()
    }

    fun setTrafficMultiplier(multiplier: Double) {
        _trafficMultiplier.value = multiplier
        _comparison.value = calculateCurrentComparison()
    }

    fun toggleRain() {
        _isRaining.value = !_isRaining.value
        _comparison.value = calculateCurrentComparison()
    }

    private fun calculateCurrentComparison(): CommuteComparison {
        val distance = BangaloreLocations.estimateRoadDistanceKm(_fromHub.value, _toHub.value)
        return BangaloreTariff.computeCommuteComparison(
            distanceKm = distance,
            trafficMultiplier = _trafficMultiplier.value,
            isRaining = _isRaining.value
        )
    }
}
