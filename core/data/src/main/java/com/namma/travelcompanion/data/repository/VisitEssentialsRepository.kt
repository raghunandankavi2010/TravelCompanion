package com.namma.travelcompanion.data.repository

import android.content.Context
import com.namma.travelcompanion.domain.model.Annadanam
import com.namma.travelcompanion.domain.model.Fee
import com.namma.travelcompanion.domain.model.ParkingSpot
import com.namma.travelcompanion.domain.model.PaymentModes
import com.namma.travelcompanion.domain.model.SpecialEntry
import com.namma.travelcompanion.domain.model.VisitEssentials
import com.namma.travelcompanion.domain.model.VisitEssentialsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

/**
 * Curated visitor facts. Source of truth is the bundled asset; the same file's GitLab raw URL is
 * fetched at most every 6 hours so fixes reach users without an app release. The higher `version` wins.
 * Places without a curated entry fall back to OpenStreetMap parking (assets/essentials/osm_parking.json).
 */
class VisitEssentialsRepository(private val context: Context) {

    private var data: VisitEssentialsData? = null
    private var osmParking: Map<String, List<ParkingSpot>>? = null
    private val cacheFile get() = File(context.filesDir, "visit_essentials.json")

    suspend fun forPlace(placeId: String): VisitEssentials? {
        val curated = load().places[placeId]
        if (curated != null && curated.parking.isNotEmpty()) return curated
        val osm = osmParking().get(placeId).orEmpty()
        return when {
            curated != null -> curated.copy(parking = osm)
            osm.isNotEmpty() -> VisitEssentials(placeId, lastVerified = "", sourceUrl = "", parking = osm)
            else -> null
        }
    }

    private suspend fun load(): VisitEssentialsData = data ?: withContext(Dispatchers.IO) {
        val bundled = parseVisitEssentials(readAsset(ASSET))
        val cached = runCatching { parseVisitEssentials(cacheFile.readText()) }.getOrNull()
        val best = listOfNotNull(bundled, cached).maxBy { it.version }
        val stale = !cacheFile.exists() || System.currentTimeMillis() - cacheFile.lastModified() > REFRESH_MS
        if (stale) refresh()?.takeIf { it.version > best.version } ?: best else best
    }.also { data = it }

    /** Network failure just keeps the copy we have; this data is a nice-to-have, never a blocker. */
    private fun refresh(): VisitEssentialsData? = runCatching {
        val conn = (URL(REMOTE_URL).openConnection() as HttpURLConnection).apply {
            connectTimeout = 4000
            readTimeout = 6000
        }
        conn.inputStream.bufferedReader().use { it.readText() }
            .also { parseVisitEssentials(it) } // don't cache a broken file
            .let { text -> cacheFile.writeText(text); parseVisitEssentials(text) }
    }.getOrElse { cacheFile.takeIf { it.exists() }?.setLastModified(System.currentTimeMillis()); null }

    private suspend fun osmParking(): Map<String, List<ParkingSpot>> = osmParking ?: withContext(Dispatchers.IO) {
        runCatching { parseOsmParking(readAsset(OSM_ASSET)) }.getOrDefault(emptyMap())
    }.also { osmParking = it }

    private fun readAsset(path: String) = context.assets.open(path).bufferedReader().use { it.readText() }

    companion object {
        private const val ASSET = "essentials/visit_essentials.json"
        private const val OSM_ASSET = "essentials/osm_parking.json"
        const val REMOTE_URL =
            "https://gitlab.com/raghunandan2005/travelcompanion/-/raw/main/app/src/main/assets/essentials/visit_essentials.json"
        const val REPORT_URL = "https://gitlab.com/raghunandan2005/travelcompanion/-/issues/new"
        private const val REFRESH_MS = 6 * 60 * 60 * 1000L // a ~20 KB file; fixes reach phones within hours
    }
}

fun parseVisitEssentials(json: String): VisitEssentialsData {
    val root = JSONObject(json)
    val places = root.getJSONArray("places").objects().map { o ->
        VisitEssentials(
            placeId = o.getString("placeId"),
            wikidataId = o.optString("wikidataId"),
            lastVerified = o.optString("lastVerified"),
            sourceUrl = o.optString("sourceUrl"),
            unverified = o.optBoolean("unverified"),
            payment = o.optJSONObject("payment")?.let {
                PaymentModes(it.optBoolean("cash", true), it.optBoolean("upi"), it.optBoolean("card"), it.optString("note"))
            },
            fees = o.optJSONArray("fees").objects().map { Fee(it.getString("label"), it.getInt("inr")) },
            timings = o.optString("timings"),
            closedOn = o.optJSONArray("closedOn").strings(),
            specialEntry = o.optJSONArray("specialEntry").objects().map {
                SpecialEntry(it.getString("name"), it.optInt("inr", -1).takeIf { p -> p >= 0 }, it.optString("bookUrl"), it.optString("note"))
            },
            annadanam = o.optJSONObject("annadanam")?.let { Annadanam(it.optBoolean("available"), it.optString("timings"), it.optString("note")) },
            parking = o.optJSONArray("parking").objects().map {
                ParkingSpot(it.optString("name", "Parking"), it.getDouble("lat"), it.getDouble("lng"), it.optString("fee"))
            },
            dressCode = o.optString("dressCode"),
            footwear = o.optString("footwear"),
            photography = o.optString("photography"),
            carry = o.optJSONArray("carry").strings(),
            warnings = o.optJSONArray("warnings").strings(),
        )
    }
    val byId = places.associateBy { it.placeId } + places.filter { it.wikidataId.isNotBlank() }.associateBy { it.wikidataId }
    return VisitEssentialsData(root.optInt("version"), byId)
}

fun parseOsmParking(json: String): Map<String, List<ParkingSpot>> {
    val places = JSONObject(json).getJSONObject("places")
    return places.keys().asSequence().associateWith { id ->
        places.getJSONArray(id).objects().map {
            ParkingSpot(it.optString("name", "Parking"), it.getDouble("lat"), it.getDouble("lng"), it.optString("fee"), fromOsm = true,
                distanceM = if (it.has("distanceM")) it.getInt("distanceM") else null)
        }
    }
}

private fun JSONArray?.objects(): List<JSONObject> = if (this == null) emptyList() else List(length()) { getJSONObject(it) }
private fun JSONArray?.strings(): List<String> = if (this == null) emptyList() else List(length()) { getString(it) }
