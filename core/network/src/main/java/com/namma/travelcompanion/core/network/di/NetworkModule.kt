package com.namma.travelcompanion.core.network.di

import com.namma.travelcompanion.core.network.api.KtorTravelApiService
import com.namma.travelcompanion.core.network.client.KtorClientProvider
import org.koin.dsl.module

val networkModule = module {
    single { KtorClientProvider.createClient() }
    single { KtorTravelApiService(get()) }
}
