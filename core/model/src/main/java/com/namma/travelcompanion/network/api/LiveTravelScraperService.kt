package com.namma.travelcompanion.network.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ScrapedSpotInfo(
    val title: String,
    val description: String,
    val extract: String,
    val thumbnailUrl: String,
    val pageUrl: String,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val isLiveScraped: Boolean = true,
    val copyrightLicense: String = "CC BY-SA 4.0 / Wikimedia Commons",
    val fetchedTimestamp: String = ""
)

data class LiveTravelAdvisory(
    val id: String,
    val category: String,
    val destination: String,
    val title: String,
    val currentStatus: String,
    val urgencyLevel: String, // "CRITICAL", "CAUTION", "NORMAL"
    val groundTruthAdvice: String,
    val lastVerified: String
)

data class LiveWeatherBrief(
    val zoneName: String,
    val tempCelsius: String,
    val condition: String,
    val rainProbability: String,
    val bestHoursToExplore: String,
    val advisoryTag: String,
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val isLive: Boolean = false
)

object LiveTravelScraperService {

    private val offlineFallbackData: Map<String, ScrapedSpotInfo> = mapOf(
        "hampi" to ScrapedSpotInfo(
            title = "Hampi (Vijayanagara)",
            description = "UNESCO World Heritage Site in Ballari/Vijayanagara district",
            extract = "Hampi is a UNESCO World Heritage Site located in east-central Karnataka. It was the capital of the Vijayanagara Empire in the 14th century.",
            thumbnailUrl = "https://thumb.wikimedia.org/wikipedia/commons/thumb/d/dd/Wide_angle_of_Galigopuram_of_Virupaksha_Temple%2C_Hampi_%2804%29.jpg/640px-Wide_angle_of_Galigopuram_of_Virupaksha_Temple%2C_Hampi_%2804%29.jpg",
            pageUrl = "https://en.wikipedia.org/wiki/Hampi",
            latitude = 15.335,
            longitude = 76.460,
            isLiveScraped = false,
            copyrightLicense = "CC BY-SA 4.0 Wikimedia Commons",
            fetchedTimestamp = "Offline Verified"
        ),
        "murudeshwara" to ScrapedSpotInfo(
            title = "Murudeshwar",
            description = "Town in Bhatkal Taluk, coastal Karnataka",
            extract = "Murdeshwar is famous for the world's second-tallest Shiva statue, towering at 123 feet (37 m) on the Arabian Sea coast of Uttara Kannada.",
            thumbnailUrl = "https://thumb.wikimedia.org/wikipedia/commons/thumb/1/10/Shiva_Statue_at_Murudeshwara_Temple.jpg/640px-Shiva_Statue_at_Murudeshwara_Temple.jpg",
            pageUrl = "https://en.wikipedia.org/wiki/Murdeshwar",
            latitude = 14.094,
            longitude = 74.484,
            isLiveScraped = false,
            copyrightLicense = "CC BY-SA 4.0 Wikimedia Commons",
            fetchedTimestamp = "Offline Verified"
        ),
        "kudremukh" to ScrapedSpotInfo(
            title = "Kudremukh Peak & National Park",
            description = "Mountain range in Chikkamagaluru, Western Ghats",
            extract = "Kudremukh is a mountain peak situated in Chikkamagaluru district. At 1,894 metres, it is known for pristine Shola grasslands.",
            thumbnailUrl = "https://thumb.wikimedia.org/wikipedia/commons/thumb/9/9f/Kudremukh_Peak_View.jpg/640px-Kudremukh_Peak_View.jpg",
            pageUrl = "https://en.wikipedia.org/wiki/Kudremukh",
            latitude = 13.216,
            longitude = 75.250,
            isLiveScraped = false,
            copyrightLicense = "CC BY-SA 4.0 Wikimedia Commons",
            fetchedTimestamp = "Offline Verified"
        )
    )

    val liveAdvisories: List<LiveTravelAdvisory> = listOf(
        LiveTravelAdvisory(
            id = "bandipur_night_ban",
            category = "Wildlife Corridor",
            destination = "Bandipur & Nagarhole Tiger Reserves",
            title = "Strict 9:00 PM – 6:00 AM Night Traffic Closure",
            currentStatus = "Active Night Ban Enforced Daily",
            urgencyLevel = "CRITICAL",
            groundTruthAdvice = "Forest Dept gates at Gundlupet and Melukamanahalli close strictly at 9:00 PM. No vehicles allowed overnight to safeguard wildlife.",
            lastVerified = "Karnataka Forest Dept Rule"
        ),
        LiveTravelAdvisory(
            id = "aranya_kudremukh_quota",
            category = "Trekking Permits",
            destination = "Kudremukh & Kumara Parvatha",
            title = "Strict 50 Trekkers/Day Cap via Aranya Vihaara",
            currentStatus = "Online Permits Only (No Spot Counter)",
            urgencyLevel = "CAUTION",
            groundTruthAdvice = "Zero spot permits issued at Balagal checkpost. You MUST reserve at aranyavihaara.karnataka.gov.in up to 30 days ahead.",
            lastVerified = "Aranya Vihaara Official"
        )
    )

    val regionalWeatherBriefs: List<LiveWeatherBrief> = listOf(
        LiveWeatherBrief(
            zoneName = "Malnad & Ghats (Chikkamagaluru, Coorg, Agumbe)",
            tempCelsius = "22°C",
            condition = "Lush & Misty / Mild Showers",
            rainProbability = "35%",
            bestHoursToExplore = "7:00 AM – 11:30 AM & 4:00 PM – 6:30 PM",
            lat = 13.3161, lng = 75.7720,
            advisoryTag = "Carry leech socks & rain gear for peak hikes"
        ),
        LiveWeatherBrief(
            zoneName = "Coastal Karavali (Gokarna, Udupi, Mangaluru)",
            tempCelsius = "29°C",
            condition = "Sunny Breeze / Moderate Waves",
            rainProbability = "15%",
            bestHoursToExplore = "Early Morning & 4:30 PM Sunset",
            lat = 13.3409, lng = 74.7421,
            advisoryTag = "Hydrate well; high humidity along cliff trails"
        )
    )

    suspend fun fetchLiveWeather(): List<LiveWeatherBrief> = withContext(Dispatchers.IO) {
        regionalWeatherBriefs.map { brief ->
            runCatching {
                val url = URL(
                    "https://api.open-meteo.com/v1/forecast?latitude=${brief.lat}&longitude=${brief.lng}" +
                        "&current=temperature_2m,weather_code&daily=precipitation_probability_max" +
                        "&timezone=Asia%2FKolkata&forecast_days=1"
                )
                val conn = (url.openConnection() as HttpURLConnection).apply {
                    connectTimeout = 5000
                    readTimeout = 5000
                }
                val json = conn.inputStream.bufferedReader().use { JSONObject(it.readText()) }
                val current = json.getJSONObject("current")
                val rain = json.getJSONObject("daily").getJSONArray("precipitation_probability_max").optInt(0, -1)
                brief.copy(
                    tempCelsius = "${current.getDouble("temperature_2m").toInt()}°C",
                    condition = weatherCodeText(current.getInt("weather_code")),
                    rainProbability = if (rain >= 0) "$rain%" else brief.rainProbability,
                    isLive = true
                )
            }.getOrDefault(brief)
        }
    }

    internal fun weatherCodeText(code: Int): String = when (code) {
        0 -> "Clear sky"
        1, 2 -> "Partly cloudy"
        3 -> "Overcast"
        45, 48 -> "Fog"
        in 51..57 -> "Drizzle"
        in 61..67, in 80..82 -> "Rain"
        in 71..77, 85, 86 -> "Snow"
        in 95..99 -> "Thunderstorm"
        else -> "Unsettled"
    }

    suspend fun scrapeLiveTravelInfo(searchQuery: String): ScrapedSpotInfo = withContext(Dispatchers.IO) {
        val cleanQuery = searchQuery.trim()
        val normalizedKey = cleanQuery.lowercase().replace(" ", "_")
        val fallback = offlineFallbackData.entries.firstOrNull { normalizedKey.contains(it.key) }?.value

        try {
            val encodedTitle = URLEncoder.encode(cleanQuery.replace(" ", "_"), "UTF-8")
            val url = URL("https://en.wikipedia.org/api/rest_v1/page/summary/$encodedTitle")
            val connection = (url.openConnection() as HttpURLConnection).apply {
                connectTimeout = 5000
                readTimeout = 5000
                setRequestProperty("User-Agent", "NammaTravelCompanion/1.0")
            }
            if (connection.responseCode == 200) {
                val text = connection.inputStream.bufferedReader().use { it.readText() }
                val json = JSONObject(text)
                val title = json.optString("title", cleanQuery)
                val description = json.optString("description", "Tourist destination in Karnataka")
                val extract = json.optString("extract", "")
                val thumb = json.optJSONObject("thumbnail")?.optString("source", "") ?: (fallback?.thumbnailUrl ?: "")
                val timestamp = SimpleDateFormat("HH:mm:ss, dd MMM yyyy", Locale.getDefault()).format(Date())
                return@withContext ScrapedSpotInfo(
                    title = title,
                    description = description,
                    extract = if (extract.isNotBlank()) extract else (fallback?.extract ?: ""),
                    thumbnailUrl = thumb,
                    pageUrl = "https://en.wikipedia.org/wiki/$encodedTitle",
                    isLiveScraped = true,
                    fetchedTimestamp = "Live: $timestamp"
                )
            }
        } catch (_: Exception) {}

        return@withContext fallback ?: ScrapedSpotInfo(
            title = cleanQuery,
            description = "Karnataka Travel Destination",
            extract = "$cleanQuery is a renowned travel destination in Karnataka.",
            thumbnailUrl = "",
            pageUrl = "https://karnatakatourism.org",
            isLiveScraped = false,
            fetchedTimestamp = "Offline Archive"
        )
    }
}
