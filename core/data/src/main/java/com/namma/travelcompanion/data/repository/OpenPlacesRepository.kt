package com.namma.travelcompanion.data.repository

import android.content.Context
import com.namma.travelcompanion.domain.model.ChannelVideo
import com.namma.travelcompanion.domain.model.DistrictShape
import com.namma.travelcompanion.domain.model.GeoPoint
import com.namma.travelcompanion.domain.model.OpenPlace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray

/**
 * Bundled open data: Karnataka places from Wikidata (tools/fetch_places.py), district outlines
 * (tools/build_districts.py) and videos from the author's YouTube channel (tools/refresh_channel_videos.py).
 * Each file is parsed once, on first use.
 */
class OpenPlacesRepository(private val context: Context) {

    private var places: List<OpenPlace>? = null
    private var videos: List<ChannelVideo>? = null
    private var shapes: List<DistrictShape>? = null

    suspend fun places(): List<OpenPlace> = places ?: withContext(Dispatchers.IO) {
        val json = JSONArray(readAsset("places/karnataka_places.json"))
        List(json.length()) { i ->
            val o = json.getJSONObject(i)
            OpenPlace(
                id = o.getString("id"),
                name = o.getString("name"),
                description = o.optString("description"),
                category = o.getString("category"),
                lat = o.getDouble("lat"),
                lng = o.getDouble("lng"),
                district = o.optString("district"),
                wikipediaTitle = o.getString("wikipediaTitle"),
                image = o.optString("image"),
                worldHeritage = o.optBoolean("worldHeritage"),
                districtId = o.optString("districtId"),
                source = o.optString("source", "wikidata")
            )
        }
    }.also { places = it }

    suspend fun channelVideos(): List<ChannelVideo> = videos ?: withContext(Dispatchers.IO) {
        val json = JSONArray(readAsset("youtube/channel_videos.json"))
        List(json.length()) { i -> json.getJSONObject(i).let { ChannelVideo(it.getString("id"), it.getString("title")) } }
    }.also { videos = it }

    /** District outlines for the map (tools/build_districts.py; geoBoundaries, ODbL). */
    suspend fun districtShapes(): List<DistrictShape> = shapes ?: withContext(Dispatchers.IO) {
        val json = JSONArray(readAsset("maps/karnataka_districts.json"))
        List(json.length()) { i ->
            val o = json.getJSONObject(i)
            val polygons = o.getJSONArray("polygons")
            DistrictShape(
                districtId = o.getString("id"),
                polygons = List(polygons.length()) { p -> polygons.getJSONArray(p).toPoints() },
                label = o.getJSONArray("label").let { GeoPoint(it.getDouble(0), it.getDouble(1)) }
            )
        }
    }.also { shapes = it }

    private fun JSONArray.toPoints() = List(length()) { i -> getJSONArray(i).let { GeoPoint(it.getDouble(0), it.getDouble(1)) } }

    private fun readAsset(path: String) = context.assets.open(path).bufferedReader().use { it.readText() }

    companion object {
        const val CHANNEL_URL = "https://www.youtube.com/channel/UCnBJaoGj6EUNvTlIqLWGG8A"
    }
}
