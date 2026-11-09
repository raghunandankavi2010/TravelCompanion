package com.namma.travelcompanion.domain.model

import java.net.URLEncoder

/**
 * A place from Wikidata (CC0). Only facts ship with the app; the photo is loaded from Wikimedia Commons and the
 * long description from Wikipedia at runtime, each credited and linked to its source.
 */
data class OpenPlace(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val lat: Double,
    val lng: Double,
    val district: String,
    val wikipediaTitle: String,
    val image: String,
    val worldHeritage: Boolean,
    /** [District.id], assigned at build time by point-in-polygon (tools/build_districts.py). */
    val districtId: String = "",
    /** "wikidata", "osm" or "local" (tools/local_places.csv); decides the attribution shown. */
    val source: String = "wikidata"
) {
    val hasImage: Boolean get() = image.isNotBlank()
    val hasCoordinates: Boolean get() = lat != 0.0 || lng != 0.0
    val hasWikipedia: Boolean get() = wikipediaTitle.isNotBlank()

    /** Commons serves a resized thumbnail for this URL (redirect to upload.wikimedia.org). */
    fun imageUrl(width: Int): String = "https://commons.wikimedia.org/wiki/Special:FilePath/${image.urlEncoded()}?width=$width"

    val imageCreditUrl: String get() = "https://commons.wikimedia.org/wiki/File:${image.urlEncoded()}"
    val wikipediaUrl: String get() = "https://en.wikipedia.org/wiki/${wikipediaTitle.urlEncoded()}"
    val wikidataUrl: String get() = "https://www.wikidata.org/wiki/$id"

    private fun String.urlEncoded() = URLEncoder.encode(replace(' ', '_'), "UTF-8").replace("+", "%20")
}

object PlaceSearch {

    /** Case-insensitive match on name, district and description; [category] null means all. */
    fun filter(places: List<OpenPlace>, query: String, category: String?): List<OpenPlace> {
        val q = query.trim().lowercase()
        return places.filter { place ->
            (category == null || place.category == category) &&
                (q.isEmpty() || listOf(place.name, place.district, place.description).any { q in it.lowercase() })
        }
    }

    fun categories(places: List<OpenPlace>): List<String> =
        places.groupingBy { it.category }.eachCount().entries.sortedByDescending { it.value }.map { it.key }
}

data class ChannelVideo(val id: String, val title: String) {
    val thumbnailUrl: String get() = "https://i.ytimg.com/vi/$id/hqdefault.jpg"
    val watchUrl: String get() = "https://www.youtube.com/watch?v=$id"
}

object ChannelVideoMatcher {

    // Words that appear in many place names and say nothing about which place a video shows.
    private val genericWords = setOf(
        "temple", "falls", "fort", "palace", "hills", "hill", "lake", "garden", "gardens", "botanical", "national",
        "park", "wildlife", "sanctuary", "museum", "beach", "betta", "ghat", "view", "point", "viewpoint", "sri",
        "shree", "shri", "the", "of", "and", "karnataka", "india", "river", "dam"
    ) + placeNameWords()

    // District and HQ names (plus old spellings) appear in many video titles, so they never count as the key word.
    private fun placeNameWords(): Set<String> =
        (KarnatakaDistricts.all.flatMap { listOf(it.name, it.headquarters) } +
            listOf("Bangalore", "Mysore", "Mangalore", "Coorg", "Hubli", "Belgaum", "Gulbarga", "Bijapur", "Shimoga", "Tumkur"))
            .flatMap { it.lowercase().split(Regex("[^\\p{L}]+")) }
            .toSet()

    /**
     * Videos whose title names the place: the full name, or its most specific word (5+ letters, not generic or a district name),
     * e.g. "Savandurga" or "Lalbagh". Keeps "Mysore Palace" from matching every Mysuru video.
     */
    fun videosFor(place: OpenPlace, videos: List<ChannelVideo>): List<ChannelVideo> {
        val name = place.name.lowercase()
        val keyWord = name.split(Regex("[^\\p{L}]+"))
            .filter { it.length >= 5 && it !in genericWords }
            .maxByOrNull { it.length }
        return videos.filter { video ->
            val title = video.title.lowercase()
            name in title || (keyWord != null && keyWord in title)
        }
    }
}
