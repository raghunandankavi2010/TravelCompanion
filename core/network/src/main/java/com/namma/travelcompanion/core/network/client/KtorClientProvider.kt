package com.namma.travelcompanion.core.network.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClientProvider {

    fun createClient(engine: HttpClientEngine = Android.create()): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        prettyPrint = false
                    }
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 6_000
                connectTimeoutMillis = 6_000
                socketTimeoutMillis = 6_000
            }
            defaultRequest {
                header("User-Agent", "NammaTravelCompanion/2.0 (Android; info@travelcompanion.internal)")
            }
        }
    }
}
