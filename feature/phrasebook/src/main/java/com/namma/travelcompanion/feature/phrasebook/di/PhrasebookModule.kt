package com.namma.travelcompanion.feature.phrasebook.di

import com.namma.travelcompanion.feature.phrasebook.data.translator.CommuteTranslator
import com.namma.travelcompanion.feature.phrasebook.ui.KannadaSathiViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val phrasebookModule = module {
    single { CommuteTranslator(get()) }
    viewModel { KannadaSathiViewModel(get(), get(), get(), get()) }
}
