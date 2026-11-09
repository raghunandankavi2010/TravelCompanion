package com.namma.travelcompanion.presentation.safety.di

import com.namma.travelcompanion.presentation.lifeline.LifelineViewModel
import com.namma.travelcompanion.presentation.safety.NightGuardianViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val safetyModule = module {
    viewModel { LifelineViewModel() }
    viewModel { NightGuardianViewModel(get(), get(), get()) }
}
