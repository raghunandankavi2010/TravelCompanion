package com.namma.travelcompanion.core.network.di

import com.namma.travelcompanion.core.network.api.KtorTravelApiService
import com.namma.travelcompanion.core.network.client.KtorClientProvider
import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> { KtorClientProvider.createClient() }
    single<KtorTravelApiService> { KtorTravelApiService(get()) }
}
