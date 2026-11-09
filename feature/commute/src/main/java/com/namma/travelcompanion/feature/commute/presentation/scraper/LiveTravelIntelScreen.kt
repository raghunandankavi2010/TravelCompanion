package com.namma.travelcompanion.feature.commute.presentation.scraper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.core.ui.components.openGeoCoordinates
import com.namma.travelcompanion.core.ui.components.openMapsSearch
import com.namma.travelcompanion.core.ui.components.openUrl
import com.namma.travelcompanion.feature.commute.presentation.scraper.components.AdvisoryCard
import com.namma.travelcompanion.feature.commute.presentation.scraper.components.AdvisoryHeader
import com.namma.travelcompanion.feature.commute.presentation.scraper.components.ScrapeSearchCard
import com.namma.travelcompanion.feature.commute.presentation.scraper.components.ScrapedResultCard
import com.namma.travelcompanion.feature.commute.presentation.scraper.components.TravelIntelHeader
import com.namma.travelcompanion.feature.commute.presentation.scraper.components.WeatherBriefRow
import com.namma.travelcompanion.feature.commute.presentation.scraper.components.WeatherTitle

@Composable
fun LiveTravelIntelScreen(viewModel: LiveTravelScraperViewModel) {
    val context = LocalContext.current
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isScraping by viewModel.isScraping.collectAsState()
    val scrapedResult by viewModel.scrapedResult.collectAsState()
    val isSyncingAdvisories by viewModel.isRefreshingWeather.collectAsState()
    val weatherBriefs by viewModel.weatherBriefs.collectAsState()
    val lastSyncTime by viewModel.lastSyncTime.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate900)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                TravelIntelHeader(
                    isRefreshing = isSyncingAdvisories,
                    onRefresh = { viewModel.refreshWeather() }
                )
            }

            item {
                ScrapeSearchCard(
                    query = searchQuery,
                    onQueryChange = { viewModel.setSearchQuery(it) },
                    isScraping = isScraping,
                    presetChips = viewModel.presetChips,
                    onScrape = { viewModel.scrapeDestination(it) }
                )
            }

            // Scraped Result Display Card
            item {
                scrapedResult?.let { info ->
                    ScrapedResultCard(
                        info = info,
                        onOpenWeb = { openUrl(context, info.pageUrl) },
                        onOpenMaps = {
                            if (info.latitude != null && info.longitude != null) {
                                openGeoCoordinates(context, info.latitude!!.toDouble(), info.longitude!!.toDouble(), info.title)
                            } else {
                                openMapsSearch(context, info.title)
                            }
                        }
                    )
                }
            }

            item {
                WeatherTitle()
            }

            item {
                WeatherBriefRow(weatherBriefs = weatherBriefs)
            }

            item {
                AdvisoryHeader()
            }

            items(viewModel.advisories, key = { it.id }) { advisory ->
                AdvisoryCard(advisory = advisory)
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
