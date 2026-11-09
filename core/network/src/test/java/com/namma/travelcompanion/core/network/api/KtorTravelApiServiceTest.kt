package com.namma.travelcompanion.core.network.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class KtorTravelApiServiceTest {

    @Test
    fun getWikipediaSummary_parsesJsonResponseCorrectly() = runBlocking {
        val jsonResponse = """
            {
              "title": "Mysore Palace",
              "displaytitle": "<b>Mysore Palace</b>",
              "description": "Historical palace in Mysuru, Karnataka, India",
              "extract": "Mysore Palace, also known as Amba Vilas Palace, is a historical palace in Mysuru.",
              "thumbnail": {
                "source": "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/Mysore_Palace_Morning.jpg/320px.jpg"
              },
              "content_urls": {
                "desktop": {
                  "page": "https://en.wikipedia.org/wiki/Mysore_Palace"
                }
              }
            }
        """.trimIndent()

        val mockEngine = MockEngine { _ ->
            respond(
                content = jsonResponse,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }

        val apiService = KtorTravelApiService(client)
        val result = apiService.getWikipediaSummary("Mysore Palace")

        assertEquals("Mysore Palace", result.title)
        assertEquals("Historical palace in Mysuru, Karnataka, India", result.description)
        assertEquals("https://en.wikipedia.org/wiki/Mysore_Palace", result.contentUrls?.desktop?.page)
    }
}
