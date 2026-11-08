package com.namma.travelcompanion.feature.meter.di

import com.namma.travelcompanion.feature.meter.data.api.LiveRideAggregatorService
import com.namma.travelcompanion.feature.meter.ui.calculator.FareEstimatorViewModel
import com.namma.travelcompanion.feature.meter.ui.cost.TravelCostViewModel
import com.namma.travelcompanion.feature.meter.ui.lifeline.LifelineViewModel
import com.namma.travelcompanion.feature.meter.ui.meter.MeterViewModel
import com.namma.travelcompanion.feature.meter.ui.safety.NightGuardianViewModel
import com.namma.travelcompanion.feature.meter.ui.store.ProStoreViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val meterModule = module {
    single { LiveRideAggregatorService }
    viewModel { MeterViewModel(get(), get(), get()) }
    viewModel { FareEstimatorViewModel() }
    viewModel { NightGuardianViewModel(get()) }
    viewModel { LifelineViewModel() }
    viewModel { TravelCostViewModel() }
    viewModel { ProStoreViewModel(get()) }
}
