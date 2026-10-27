package com.nammaauto.ai.presentation.cost

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nammaauto.ai.domain.model.AccommodationType
import com.nammaauto.ai.domain.model.DiningBudgetPreference
import com.nammaauto.ai.domain.model.TravelCostBreakdown
import com.nammaauto.ai.domain.model.TravelCostEngine
import com.nammaauto.ai.domain.model.TravelTransportMode
import com.nammaauto.ai.domain.model.TripDestinationItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TravelCostViewModel(application: Application) : AndroidViewModel(application) {

    val availableDestinations: List<TripDestinationItem> = TravelCostEngine.popularDestinations

    private val _selectedSpotIds = MutableStateFlow<Set<String>>(setOf("mysore_palace", "kabini"))
    val selectedSpotIds: StateFlow<Set<String>> = _selectedSpotIds.asStateFlow()

    private val _tripDays = MutableStateFlow(3)
    val tripDays: StateFlow<Int> = _tripDays.asStateFlow()

    private val _adultsCount = MutableStateFlow(2)
    val adultsCount: StateFlow<Int> = _adultsCount.asStateFlow()

    private val _childrenCount = MutableStateFlow(0)
    val childrenCount: StateFlow<Int> = _childrenCount.asStateFlow()

    private val _isForeigner = MutableStateFlow(false)
    val isForeigner: StateFlow<Boolean> = _isForeigner.asStateFlow()

    private val _transportMode = MutableStateFlow(TravelTransportMode.KSRTC_AIRAVAT_BUS)
    val transportMode: StateFlow<TravelTransportMode> = _transportMode.asStateFlow()

    private val _stayType = MutableStateFlow(AccommodationType.KSTDC_MAYURA_HOTEL)
    val stayType: StateFlow<AccommodationType> = _stayType.asStateFlow()

    private val _diningPreference = MutableStateFlow(DiningBudgetPreference.PURE_VEG_DARSHINI_MID)
    val diningPreference: StateFlow<DiningBudgetPreference> = _diningPreference.asStateFlow()

    private val _breakdown = MutableStateFlow(calculateCurrent())
    val breakdown: StateFlow<TravelCostBreakdown> = _breakdown.asStateFlow()

    fun toggleDestination(spotId: String) {
        val set = _selectedSpotIds.value.toMutableSet()
        if (set.contains(spotId)) {
            if (set.size > 1) set.remove(spotId)
        } else {
            set.add(spotId)
        }
        _selectedSpotIds.value = set
        recalculate()
    }

    fun setTripDays(days: Int) {
        if (days in 1..14) {
            _tripDays.value = days
            recalculate()
        }
    }

    fun setAdults(count: Int) {
        if (count in 1..20) {
            _adultsCount.value = count
            recalculate()
        }
    }

    fun setChildren(count: Int) {
        if (count in 0..10) {
            _childrenCount.value = count
            recalculate()
        }
    }

    fun toggleForeigner() {
        _isForeigner.value = !_isForeigner.value
        recalculate()
    }

    fun setTransportMode(mode: TravelTransportMode) {
        _transportMode.value = mode
        recalculate()
    }

    fun setStayType(stay: AccommodationType) {
        _stayType.value = stay
        recalculate()
    }

    fun setDiningPreference(pref: DiningBudgetPreference) {
        _diningPreference.value = pref
        recalculate()
    }

    private fun recalculate() {
        _breakdown.value = calculateCurrent()
    }

    private fun calculateCurrent(): TravelCostBreakdown {
        val selected = availableDestinations.filter { _selectedSpotIds.value.contains(it.id) }
        return TravelCostEngine.calculateTotalTripCost(
            selectedDestinations = selected,
            tripDays = _tripDays.value,
            adultsCount = _adultsCount.value,
            childrenCount = _childrenCount.value,
            isForeignNational = _isForeigner.value,
            transportMode = _transportMode.value,
            stayType = _stayType.value,
            diningPreference = _diningPreference.value
        )
    }
}
