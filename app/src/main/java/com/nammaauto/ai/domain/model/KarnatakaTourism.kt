package com.nammaauto.ai.domain.model

enum class TourismCategory(val label: String, val iconDescription: String) {
    TEMPLES_HERITAGE("Temples & Heritage", "Ancient Hoysala, Vijayanagara & Chalukya Architecture"),
    ADVENTURE_SPORTS("Adventure Sports", "White-Water Rafting, Scuba Diving, Surfing & Bouldering"),
    TREKKING_TRAILS("Trekking & Peaks", "Western Ghats, Shola Grasslands & Peak Trails via Aranya Vihaara"),
    BEACHES("Coastal Beaches", "Golden Sands, Watersports & Arabian Sea Sunsets"),
    ISLANDS("Islands to Visit", "Geological Columnar Basalt & Coral Reef Marine Islands"),
    WATERFALLS_NATURE("Waterfalls & Rainforests", "Jog Falls, Agumbe, Shivanasamudra & Plunges"),
    WILDLIFE_SAFARI("Wildlife & Tiger Safaris", "Kabini, Bandipur, Nagarhole & Jungle Lodges")
}

enum class KarnatakaRegion(val label: String, val districtsDescription: String, val iconTag: String) {
    ALL("All Karnataka", "All 31 Districts Statewide", "🌟"),
    UTTARA_KARNATAKA("Heritage North", "Hampi, Badami, Bijapur, Bidar, Belagavi, Hubballi", "🏛️"),
    KARAVALI("Coastal Karavali", "Udupi, Gokarna, Mangaluru, Karwar, Murudeshwar", "🌊"),
    MALNAD("Malnad & Ghats", "Chikkamagaluru, Shivamogga, Coorg, Sakleshpur, Jog Falls", "🌿"),
    HALE_MYSURU("Royal South", "Mysuru, Kabini, Bandipur, Srirangapatna, Mandya", "👑"),
    BAYALUSEEME("Central Deccan", "Chitradurga, Ramanagara, Chikkaballapura, Hassan", "🏰")
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
    val region: KarnatakaRegion,
    val district: String,
    val nearestTransitHub: String,
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
    val survivalKannadaMeaning: String,
    val imageUrl: String = "",
    val videoTourUrl: String = "",
    val guideInfo: String = "Govt certified guides available at ticket counter (₹400–₹800 fixed)"
)

data class RegionalDialectInfo(
    val region: KarnatakaRegion,
    val dialectName: String,
    val commonGreeting: String,
    val friendlyPhrases: List<Pair<String, String>>,
    val regionalCuisines: List<String>,
    val localTip: String
)

data class CityTransportTariff(
    val cityName: String,
    val baseFare: String,
    val perKmRate: String,
    val localAdvice: String,
    val sharedAutoOption: String
)
