package com.nammaauto.ai.domain.model

enum class TourismCategory(val label: String, val iconDescription: String) {
    TEMPLES_HERITAGE("Temples & Heritage", "Ancient Hoysala & Vijayanagara UNESCO Architecture"),
    ADVENTURE_SPORTS("Adventure Sports", "White-Water Rafting, Scuba Diving & Bouldering"),
    TREKKING_TRAILS("Trekking & Ghats", "Western Ghats, Shola Grasslands & Mountain Peaks"),
    BEACHES("Coastal Beaches", "Golden Sands, Watersports & Arabian Sea Sunsets"),
    ISLANDS("Islands to Visit", "Geological Columnar Basalt & Coral Reef Islands")
}

data class OfficialBookingInfo(
    val portalName: String,
    val portalUrl: String,
    val bookingRule: String,
    val badgeTag: String,
    val hasOnlineBooking: Boolean = true
)

data class ForeignerPricingGuidance(
    val domesticFeeInr: Int,
    val foreignerFeeInr: Int,
    val approxUsdEquivalent: String,
    val feeIncludes: String,
    val dressCodeRules: String,
    val photographyPolicy: String,
    val scamAlertNotes: String,
    val cashVsDigitalNotes: String
)

data class KarnatakaSpot(
    val id: String,
    val name: String,
    val kannadaName: String,
    val tagline: String,
    val category: TourismCategory,
    val district: String,
    val distanceFromBangaloreKm: Int,
    val bestSeason: String,
    val timings: String,
    val overview: String,
    val keyHighlights: List<String>,
    val bookingInfo: OfficialBookingInfo,
    val foreignerPricing: ForeignerPricingGuidance,
    val howToReachTransit: String,
    val survivalKannadaPhrase: String,
    val survivalKannadaLipi: String,
    val survivalKannadaMeaning: String
)
