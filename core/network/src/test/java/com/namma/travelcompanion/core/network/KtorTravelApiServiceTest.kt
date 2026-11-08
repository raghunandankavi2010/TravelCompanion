package com.namma.travelcompanion.core.network

import com.namma.travelcompanion.core.network.api.KtorTravelApiService
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class KtorTravelApiServiceTest {

    @Test
    fun `getWikipediaSummary parses response correctly on HTTP 200`() = runTest {
        val mockEngine = MockEngine { request ->
            respond(
                content = """
                    {
                        "title": "Hampi",
                        "description": "UNESCO World Heritage Site in Karnataka",
                        "extract": "Hampi is a UNESCO World Heritage Site located in east-central Karnataka.",
                        "thumbnail": {
                            "source": "https://example.com/hampi.jpg",
                            "width": 300,
                            "height": 200
                        }
                    }
                """.trimIndent(),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }

        val service = KtorTravelApiService(client)
        val result = service.getWikipediaSummary("Hampi")

        assertEquals("Hampi", result.title)
        assertEquals("UNESCO World Heritage Site in Karnataka", result.description)
        assertEquals("https://example.com/hampi.jpg", result.thumbnail?.source)
    }

    @Test
    fun `translateText parses translation response correctly`() = runTest {
        val mockEngine = MockEngine { request ->
            respond(
                content = """
                    {
                        "responseData": {
                            "translatedText": "ಹೋಗೋಣ",
                            "match": 0.85
                        },
                        "responseStatus": 200
                    }
                """.trimIndent(),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }

        val service = KtorTravelApiService(client)
        val result = service.translateText("Let us go", "en|kn")

        assertEquals("ಹೋಗೋಣ", result.responseData?.translatedText)
    }
}
