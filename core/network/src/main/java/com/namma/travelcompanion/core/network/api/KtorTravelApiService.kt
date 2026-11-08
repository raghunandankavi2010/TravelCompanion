package com.namma.travelcompanion.core.network.api

import com.namma.travelcompanion.core.network.model.MyMemoryTranslationResponseDto
import com.namma.travelcompanion.core.network.model.WikiPageSummaryDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.encodeURLPathPart

class KtorTravelApiService(private val httpClient: HttpClient) {

    suspend fun getWikipediaSummary(titleQuery: String): WikiPageSummaryDto {
        val encodedTitle = titleQuery.trim().replace(" ", "_").encodeURLPathPart()
        return httpClient.get("https://en.wikipedia.org/api/rest_v1/page/summary/$encodedTitle").body()
    }

    suspend fun translateText(text: String, langPair: String = "en|kn"): MyMemoryTranslationResponseDto {
        return httpClient.get("https://api.mymemory.translated.net/get") {
            parameter("q", text)
            parameter("langpair", langPair)
        }.body()
    }
}
