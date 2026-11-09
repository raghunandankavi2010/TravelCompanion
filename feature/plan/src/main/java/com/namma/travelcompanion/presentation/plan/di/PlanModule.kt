package com.namma.travelcompanion.presentation.plan.di

import com.namma.travelcompanion.presentation.calculator.FareEstimatorViewModel
import com.namma.travelcompanion.presentation.favorites.FavoritesViewModel
import com.namma.travelcompanion.presentation.tourism.KarnatakaExplorerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val planModule = module {
    viewModel { KarnatakaExplorerViewModel(get(), get()) }
    viewModel { FavoritesViewModel(get()) }
    viewModel { FareEstimatorViewModel() }
}
