package com.namma.travelcompanion.data.di

import com.namma.travelcompanion.data.billing.BillingManager
import com.namma.travelcompanion.data.location.GpsTelemetryService
import com.namma.travelcompanion.data.repository.EmergencyContactStore
import com.namma.travelcompanion.data.repository.FavoritesManager
import com.namma.travelcompanion.data.repository.OpenPlacesRepository
import com.namma.travelcompanion.data.repository.VisitEssentialsRepository
import com.namma.travelcompanion.data.speech.KannadaTtsEngine
import com.namma.travelcompanion.data.speech.SpeechToTextManager
import kotlinx.coroutines.MainScope
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val coreDataModule = module {
    single { MainScope() }
    single { BillingManager(androidApplication(), get()) }
    single { FavoritesManager(androidApplication()) }
    single { EmergencyContactStore(androidApplication()) }
    single { OpenPlacesRepository(androidApplication()) }
    single { VisitEssentialsRepository(androidApplication()) }

    factory { KannadaTtsEngine(androidApplication()) }
    factory { SpeechToTextManager(androidApplication()) }
    factory { GpsTelemetryService(androidApplication()) }
}
