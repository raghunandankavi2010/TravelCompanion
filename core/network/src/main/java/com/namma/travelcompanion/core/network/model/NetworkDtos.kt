package com.namma.travelcompanion.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class WikiPageSummaryDto(
    val title: String = "",
    val description: String = "",
    val extract: String = "",
    val thumbnail: WikiThumbnailDto? = null,
    val content_urls: WikiContentUrlsDto? = null,
    val coordinates: WikiCoordinatesDto? = null
)

@Serializable
data class WikiThumbnailDto(
    val source: String = "",
    val width: Int = 0,
    val height: Int = 0
)

@Serializable
data class WikiContentUrlsDto(
    val desktop: WikiUrlDetailsDto? = null,
    val mobile: WikiUrlDetailsDto? = null
)

@Serializable
data class WikiUrlDetailsDto(
    val page: String = ""
)

@Serializable
data class WikiCoordinatesDto(
    val lat: Double = 0.0,
    val lon: Double = 0.0
)

@Serializable
data class MyMemoryTranslationResponseDto(
    val responseData: MyMemoryResponseDataDto? = null,
    val responseStatus: Int = 0
)

@Serializable
data class MyMemoryResponseDataDto(
    val translatedText: String = "",
    val match: Double = 0.0
)
