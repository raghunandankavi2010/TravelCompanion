package com.nammaauto.ai.presentation.scraper

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nammaauto.ai.data.api.LiveTravelAdvisory
import com.nammaauto.ai.data.api.LiveTravelScraperService
import com.nammaauto.ai.data.api.LiveWeatherBrief
import com.nammaauto.ai.data.api.ScrapedSpotInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LiveTravelScraperViewModel(application: Application) : AndroidViewModel(application) {

    private val _searchQuery = MutableStateFlow("Hampi")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isScraping = MutableStateFlow(false)
    val isScraping: StateFlow<Boolean> = _isScraping.asStateFlow()

    private val _scrapedResult = MutableStateFlow<ScrapedSpotInfo?>(null)
    val scrapedResult: StateFlow<ScrapedSpotInfo?> = _scrapedResult.asStateFlow()

    private val _isSyncingAdvisories = MutableStateFlow(false)
    val isSyncingAdvisories: StateFlow<Boolean> = _isSyncingAdvisories.asStateFlow()

    private val _lastSyncTime = MutableStateFlow(getCurrentTime())
    val lastSyncTime: StateFlow<String> = _lastSyncTime.asStateFlow()

    val advisories: List<LiveTravelAdvisory> = LiveTravelScraperService.liveAdvisories
    val weatherBriefs: List<LiveWeatherBrief> = LiveTravelScraperService.regionalWeatherBriefs

    val presetChips = listOf(
        "Hampi",
        "Murudeshwara",
        "Kudremukh",
        "Bandipur National Park",
        "Udupi Sri Krishna Matha",
        "Jog Falls",
        "Belur, Karnataka",
        "Dharmasthala",
        "Badami cave temples",
        "Gokarna, India"
    )

    init {
        scrapeDestination("Hampi")
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun scrapeDestination(spotName: String) {
        if (spotName.isBlank()) return
        _searchQuery.value = spotName
        viewModelScope.launch {
            _isScraping.value = true
            val result = LiveTravelScraperService.scrapeLiveTravelInfo(spotName)
            _scrapedResult.value = result
            _isScraping.value = false
            _lastSyncTime.value = getCurrentTime()
        }
    }

    fun refreshAdvisories() {
        viewModelScope.launch {
            _isSyncingAdvisories.value = true
            delay(700)
            _lastSyncTime.value = getCurrentTime()
            _isSyncingAdvisories.value = false
        }
    }

    private fun getCurrentTime(): String {
        return SimpleDateFormat("HH:mm:ss, dd MMM yyyy", Locale.getDefault()).format(Date())
    }
}
