package com.namma.travelcompanion.feature.commute.presentation.scraper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namma.travelcompanion.network.api.LiveTravelAdvisory
import com.namma.travelcompanion.network.api.LiveTravelScraperService
import com.namma.travelcompanion.network.api.LiveWeatherBrief
import com.namma.travelcompanion.network.api.ScrapedSpotInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LiveTravelScraperViewModel : ViewModel() {

    private val _searchQuery = MutableStateFlow("Hampi")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isScraping = MutableStateFlow(false)
    val isScraping: StateFlow<Boolean> = _isScraping.asStateFlow()

    private val _scrapedResult = MutableStateFlow<ScrapedSpotInfo?>(null)
    val scrapedResult: StateFlow<ScrapedSpotInfo?> = _scrapedResult.asStateFlow()

    private val _lastSyncTime = MutableStateFlow(getCurrentTime())
    val lastSyncTime: StateFlow<String> = _lastSyncTime.asStateFlow()

    val advisories: List<LiveTravelAdvisory> = LiveTravelScraperService.liveAdvisories
    private val _weatherBriefs = MutableStateFlow(LiveTravelScraperService.regionalWeatherBriefs)
    val weatherBriefs: StateFlow<List<LiveWeatherBrief>> = _weatherBriefs.asStateFlow()

    private val _isRefreshingWeather = MutableStateFlow(false)
    val isRefreshingWeather: StateFlow<Boolean> = _isRefreshingWeather.asStateFlow()

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
        refreshWeather()
    }

    fun refreshWeather() {
        viewModelScope.launch {
            _isRefreshingWeather.value = true
            _weatherBriefs.value = LiveTravelScraperService.fetchLiveWeather()
            _lastSyncTime.value = getCurrentTime()
            _isRefreshingWeather.value = false
        }
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

    private fun getCurrentTime(): String {
        return SimpleDateFormat("HH:mm:ss, dd MMM yyyy", Locale.getDefault()).format(Date())
    }
}
