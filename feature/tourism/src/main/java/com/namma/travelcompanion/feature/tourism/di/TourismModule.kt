package com.namma.travelcompanion.feature.tourism.di

import com.namma.travelcompanion.feature.tourism.data.api.LiveTourismPriceService
import com.namma.travelcompanion.feature.tourism.data.api.LiveTravelScraperService
import com.namma.travelcompanion.feature.tourism.ui.KarnatakaExplorerViewModel
import com.namma.travelcompanion.feature.tourism.ui.LiveTravelScraperViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val tourismModule = module {
    single { LiveTourismPriceService }
    single { LiveTravelScraperService }
    viewModel { KarnatakaExplorerViewModel(get(), get()) }
    viewModel { LiveTravelScraperViewModel() }
}
