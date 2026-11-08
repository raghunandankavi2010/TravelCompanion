package com.namma.travelcompanion.data.di

import com.namma.travelcompanion.data.billing.BillingManager
import com.namma.travelcompanion.data.location.GpsTelemetryService
import com.namma.travelcompanion.data.repository.FavoritesManager
import com.namma.travelcompanion.data.speech.KannadaTtsEngine
import com.namma.travelcompanion.data.speech.SpeechToTextManager
import org.koin.dsl.module

val coreDataModule = module {
    single { GpsTelemetryService(get()) }
    single { KannadaTtsEngine(get()) }
    single { SpeechToTextManager(get()) }
    single { FavoritesManager(get()) }
    single { BillingManager(get()) }
}
