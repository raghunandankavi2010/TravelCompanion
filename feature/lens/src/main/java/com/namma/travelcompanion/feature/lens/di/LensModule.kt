package com.namma.travelcompanion.feature.lens.di

import com.namma.travelcompanion.feature.lens.ui.BusSignboardLensViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val lensModule = module {
    viewModel { BusSignboardLensViewModel(get()) }
}
