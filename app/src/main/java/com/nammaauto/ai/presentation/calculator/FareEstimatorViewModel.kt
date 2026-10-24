package com.nammaauto.ai.presentation.calculator

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nammaauto.ai.core.util.BangaloreHub
import com.nammaauto.ai.core.util.BangaloreLocations
import com.nammaauto.ai.data.api.LiveRideAggregatorService
import com.nammaauto.ai.domain.model.BangaloreTariff
import com.nammaauto.ai.domain.model.CommuteComparison
import com.nammaauto.ai.domain.model.MultiAppRadarReport
import com.nammaauto.ai.domain.model.RideAppOption
import com.nammaauto.ai.domain.model.RidePulseEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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

    private val _radarReport = MutableStateFlow(calculateRadarReport())
    val radarReport: StateFlow<MultiAppRadarReport> = _radarReport.asStateFlow()

    private val _isFetchingLiveApi = MutableStateFlow(false)
    val isFetchingLiveApi: StateFlow<Boolean> = _isFetchingLiveApi.asStateFlow()

    private val _isLiveApiVerified = MutableStateFlow(false)
    val isLiveApiVerified: StateFlow<Boolean> = _isLiveApiVerified.asStateFlow()

    fun setFromHub(hub: BangaloreHub) {
        _fromHub.value = hub
        updateAllReports()
    }

    fun setToHub(hub: BangaloreHub) {
        _toHub.value = hub
        updateAllReports()
    }

    fun setTrafficMultiplier(multiplier: Double) {
        _trafficMultiplier.value = multiplier
        updateAllReports()
    }

    fun toggleRain() {
        _isRaining.value = !_isRaining.value
        updateAllReports()
    }

    private fun updateAllReports() {
        _comparison.value = calculateCurrentComparison()
        _radarReport.value = calculateRadarReport()
        syncWithLiveRideApis()
    }

    private fun calculateCurrentComparison(): CommuteComparison {
        val distance = BangaloreLocations.estimateRoadDistanceKm(_fromHub.value, _toHub.value)
        return BangaloreTariff.computeCommuteComparison(
            distanceKm = distance,
            trafficMultiplier = _trafficMultiplier.value,
            isRaining = _isRaining.value
        )
    }

    private fun calculateRadarReport(): MultiAppRadarReport {
        val distance = BangaloreLocations.estimateRoadDistanceKm(_fromHub.value, _toHub.value)
        return RidePulseEngine.generateRadarReport(
            distanceKm = distance,
            fromName = _fromHub.value.name,
            toName = _toHub.value.name,
            trafficMultiplier = _trafficMultiplier.value,
            isRaining = _isRaining.value
        )
    }

    fun syncWithLiveRideApis() {
        viewModelScope.launch {
            _isFetchingLiveApi.value = true
            val from = _fromHub.value
            val to = _toHub.value
            val distance = BangaloreLocations.estimateRoadDistanceKm(from, to)

            val liveResponse = LiveRideAggregatorService.fetchLiveMultiAppEstimates(
                startLat = from.lat,
                startLng = from.lng,
                endLat = to.lat,
                endLng = to.lng,
                distanceKm = distance,
                fromName = from.name,
                toName = to.name,
                trafficMultiplier = _trafficMultiplier.value,
                isRaining = _isRaining.value
            )

            _radarReport.value = _radarReport.value.copy(options = liveResponse.options)
            _isLiveApiVerified.value = liveResponse.isLiveFromUberApi || liveResponse.isLiveFromOndc
            _isFetchingLiveApi.value = false
        }
    }

    fun launchRideApp(context: Context, option: RideAppOption) {
        val pm = context.packageManager
        val launchIntent = pm.getLaunchIntentForPackage(option.packageName)
        if (launchIntent != null) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(launchIntent)
        } else {
            val storeIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${option.packageName}")).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            try {
                context.startActivity(storeIntent)
            } catch (e: Exception) {
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=${option.packageName}")).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(webIntent)
            }
        }
    }
}
