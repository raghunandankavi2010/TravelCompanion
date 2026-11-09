package com.namma.travelcompanion.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WikiPageSummaryDto(
    @SerialName("title") val title: String? = null,
    @SerialName("displaytitle") val displayTitle: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("extract") val extract: String? = null,
    @SerialName("thumbnail") val thumbnail: WikiThumbnailDto? = null,
    @SerialName("content_urls") val contentUrls: WikiContentUrlsDto? = null,
    @SerialName("coordinates") val coordinates: WikiCoordinatesDto? = null
)

@Serializable
data class WikiThumbnailDto(
    @SerialName("source") val source: String? = null,
    @SerialName("width") val width: Int? = null,
    @SerialName("height") val height: Int? = null
)

@Serializable
data class WikiContentUrlsDto(
    @SerialName("desktop") val desktop: WikiDesktopUrlsDto? = null
)

@Serializable
data class WikiDesktopUrlsDto(
    @SerialName("page") val page: String? = null
)

@Serializable
data class WikiCoordinatesDto(
    @SerialName("lat") val lat: Double? = null,
    @SerialName("lon") val lon: Double? = null
)

@Serializable
data class MyMemoryTranslationResponseDto(
    @SerialName("responseData") val responseData: MyMemoryResponseDataDto? = null,
    @SerialName("responseStatus") val responseStatus: Int? = null
)

@Serializable
data class MyMemoryResponseDataDto(
    @SerialName("translatedText") val translatedText: String? = null,
    @SerialName("match") val match: Double? = null
)
