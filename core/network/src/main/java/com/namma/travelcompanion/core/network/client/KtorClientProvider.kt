package com.namma.travelcompanion.core.network.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClientProvider {

    fun createClient(engine: HttpClientEngine = OkHttp.create()): HttpClient {
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
            install(Logging) {
                level = LogLevel.INFO
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
