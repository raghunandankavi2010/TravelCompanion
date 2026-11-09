package com.namma.travelcompanion.presentation.explore.di

import com.namma.travelcompanion.presentation.explore.DistrictViewModel
import com.namma.travelcompanion.presentation.explore.DistrictsHomeViewModel
import com.namma.travelcompanion.presentation.explore.PlaceDetailViewModel
import com.namma.travelcompanion.presentation.food.VegFoodViewModel
import com.namma.travelcompanion.presentation.store.ProStoreViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val exploreModule = module {
    viewModel { DistrictsHomeViewModel(get()) }
    viewModel { parameters -> DistrictViewModel(get(), parameters.get()) }
    viewModel { parameters -> PlaceDetailViewModel(get(), get(), parameters.get()) }
    viewModel { VegFoodViewModel(get()) }
    viewModel { ProStoreViewModel(get()) }
}
