package com.nammaauto.ai.core.util

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

data class BangaloreHub(
    val id: String,
    val name: String,
    val landmark: String,
    val zone: String,
    val lat: Double,
    val lng: Double
)

object BangaloreLocations {
    val hubs = listOf(
        BangaloreHub("sb", "Silk Board Junction", "Central Commute Choke", "South", 12.9176, 77.6238),
        BangaloreHub("kora", "Koramangala", "Sony World Signal", "South-East", 12.9352, 77.6245),
        BangaloreHub("indira", "Indiranagar", "100 Feet Road / Metro", "East", 12.9784, 77.6408),
        BangaloreHub("hsr", "HSR Layout", "BDA Complex / 27th Main", "South-East", 12.9121, 77.6446),
        BangaloreHub("bella", "Bellandur Outer Ring Road", "RMZ EcoSpace / EcoWorld", "East", 12.9255, 77.6844),
        BangaloreHub("white", "Whitefield", "ITPL Main Gate", "East", 12.9855, 77.7289),
        BangaloreHub("ecity", "Electronic City Phase 1", "Infosys Gate 1 / Toll", "South", 12.8452, 77.6602),
        BangaloreHub("manyata", "Manyata Tech Park", "Nagavara Main Gate", "North", 13.0489, 77.6200),
        BangaloreHub("majestic", "Majestic Station", "KSR Railway / Sangolli Rayanna", "Central", 12.9774, 77.5708),
        BangaloreHub("mg", "MG Road", "Trinity Circle / Church Street", "Central", 12.9756, 77.6066),
        BangaloreHub("jayanagar", "Jayanagar 4th Block", "Complex & Cool Joint", "South", 12.9299, 77.5824),
        BangaloreHub("malles", "Malleshwaram", "8th Cross / Sampige Road", "West", 13.0031, 77.5703),
        BangaloreHub("marath", "Marathahalli", "Bridge / Multiplex Choke", "East", 12.9591, 77.6974),
        BangaloreHub("hebbal", "Hebbal Flyover", "Airport Highway Junction", "North", 13.0358, 77.5970),
        BangaloreHub("airport", "Kempegowda Intl Airport (KIAL)", "Terminal 1 & 2 Departure", "Airport", 13.1986, 77.7066),
        BangaloreHub("sarjapur", "Sarjapur Road", "Wipro Corporate Office", "East", 12.9110, 77.6880)
    )

    /**
     * Computes driving road estimate using Haversine formula scaled by Bangalore road curvature factor (1.32x).
     */
    fun estimateRoadDistanceKm(from: BangaloreHub, to: BangaloreHub): Double {
        if (from.id == to.id) return 0.5
        val earthRadiusKm = 6371.0
        val dLat = Math.toRadians(to.lat - from.lat)
        val dLng = Math.toRadians(to.lng - from.lng)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(from.lat)) * cos(Math.toRadians(to.lat)) *
                sin(dLng / 2) * sin(dLng / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        val straightLineKm = earthRadiusKm * c
        // Bangalore urban road tortuosity index is approximately 1.32x
        return (straightLineKm * 1.32 * 10.0).toInt() / 10.0
    }
}
