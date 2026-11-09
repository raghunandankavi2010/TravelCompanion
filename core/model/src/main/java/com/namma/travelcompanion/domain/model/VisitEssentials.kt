package com.namma.travelcompanion.domain.model

/**
 * Practical on-the-ground facts for one place (assets/essentials/visit_essentials.json, refreshed from GitLab).
 * Facts only, in our own words; [sourceUrl] points to the official page the user should trust over us.
 */
data class VisitEssentials(
    val placeId: String,
    /** Matching OpenPlace id, so Wikidata place pages find the curated entry too. */
    val wikidataId: String = "",
    val lastVerified: String,
    val sourceUrl: String,
    val unverified: Boolean = false,
    val payment: PaymentModes? = null,
    val fees: List<Fee> = emptyList(),
    val timings: String = "",
    val closedOn: List<String> = emptyList(),
    val specialEntry: List<SpecialEntry> = emptyList(),
    val annadanam: Annadanam? = null,
    val parking: List<ParkingSpot> = emptyList(),
    val dressCode: String = "",
    val footwear: String = "",
    val photography: String = "",
    val carry: List<String> = emptyList(),
    val warnings: List<String> = emptyList(),
)

data class PaymentModes(val cash: Boolean, val upi: Boolean, val card: Boolean, val note: String = "") {
    val cashOnly get() = cash && !upi && !card
}

data class Fee(val label: String, val inr: Int)

data class SpecialEntry(val name: String, val inr: Int?, val bookUrl: String, val note: String)

data class Annadanam(val available: Boolean, val timings: String, val note: String)

/** [fromOsm] lots come from OpenStreetMap and need the ODbL attribution line. */
data class ParkingSpot(val name: String, val lat: Double, val lng: Double, val fee: String, val fromOsm: Boolean = false, val distanceM: Int? = null)

data class VisitEssentialsData(val version: Int, val places: Map<String, VisitEssentials>)
