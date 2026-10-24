package com.nammaauto.ai.presentation.calculator

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.nammaauto.ai.core.util.BangaloreHub
import com.nammaauto.ai.core.util.BangaloreLocations
import com.nammaauto.ai.domain.model.BangaloreTariff
import com.nammaauto.ai.domain.model.CommuteComparison
import com.nammaauto.ai.domain.model.MultiAppRadarReport
import com.nammaauto.ai.domain.model.RideAppOption
import com.nammaauto.ai.domain.model.RidePulseEngine
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

    private val _radarReport = MutableStateFlow(calculateRadarReport())
    val radarReport: StateFlow<MultiAppRadarReport> = _radarReport.asStateFlow()

    fun setFromHub(hub: BangaloreHub) {
        _fromHub.value = hub
        _comparison.value = calculateCurrentComparison()
        _radarReport.value = calculateRadarReport()
    }

    fun setToHub(hub: BangaloreHub) {
        _toHub.value = hub
        _comparison.value = calculateCurrentComparison()
        _radarReport.value = calculateRadarReport()
    }

    fun setTrafficMultiplier(multiplier: Double) {
        _trafficMultiplier.value = multiplier
        _comparison.value = calculateCurrentComparison()
        _radarReport.value = calculateRadarReport()
    }

    fun toggleRain() {
        _isRaining.value = !_isRaining.value
        _comparison.value = calculateCurrentComparison()
        _radarReport.value = calculateRadarReport()
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

    fun launchRideApp(context: Context, option: RideAppOption) {
        val pm = context.packageManager
        val launchIntent = pm.getLaunchIntentForPackage(option.packageName)
        if (launchIntent != null) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(launchIntent)
        } else {
            // Open Play Store to install
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
