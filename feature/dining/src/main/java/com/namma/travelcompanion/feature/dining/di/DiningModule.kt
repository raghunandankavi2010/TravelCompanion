package com.namma.travelcompanion.feature.dining.di

import com.namma.travelcompanion.feature.dining.ui.VegFoodViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val diningModule = module {
    viewModel { VegFoodViewModel(get()) }
}
