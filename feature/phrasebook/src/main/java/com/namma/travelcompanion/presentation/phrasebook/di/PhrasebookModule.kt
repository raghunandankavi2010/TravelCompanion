package com.namma.travelcompanion.presentation.phrasebook.di

import com.namma.travelcompanion.presentation.phrasebook.KannadaSathiViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val phrasebookModule = module {
    viewModel { KannadaSathiViewModel(get(), get(), get()) }
}
