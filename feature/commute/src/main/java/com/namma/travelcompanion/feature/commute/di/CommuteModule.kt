package com.namma.travelcompanion.feature.commute.di

import com.namma.travelcompanion.feature.commute.data.analyzer.AutoMeterImageAnalyzer
import com.namma.travelcompanion.feature.commute.data.analyzer.BusSignLensAnalyzer
import com.namma.travelcompanion.feature.commute.presentation.cost.TravelCostViewModel
import com.namma.travelcompanion.feature.commute.presentation.lens.BusSignboardLensViewModel
import com.namma.travelcompanion.feature.commute.presentation.meter.MeterViewModel
import com.namma.travelcompanion.feature.commute.presentation.scraper.LiveTravelScraperViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val commuteModule = module {
    factory { BusSignLensAnalyzer(androidApplication()) }
    factory { AutoMeterImageAnalyzer() }

    viewModel { BusSignboardLensViewModel(get(), get()) }
    viewModel { MeterViewModel(get(), get(), get(), get()) }
    viewModel { LiveTravelScraperViewModel() }
    viewModel { TravelCostViewModel() }
}
