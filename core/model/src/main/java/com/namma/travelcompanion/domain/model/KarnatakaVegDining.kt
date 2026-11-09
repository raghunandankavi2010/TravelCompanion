package com.namma.travelcompanion.domain.model

enum class VegCategory(val label: String, val iconDescription: String) {
    HIGHWAY_VEG_HUB("Highway Pure Veg", "Spotless Restrooms & Quick Service for Road Trippers"),
    TEMPLE_ANNADANA("Sacred Temple Annadana", "Free Divine Sanctum Vegetarian Meals & Timings"),
    HERITAGE_TIFFIN("Heritage Tiffin Legend", "Iconic Dosa, Idli & Degree Filter Coffee Institutions"),
    NORTH_KA_KHANAVALI("North KA Khanavali", "Authentic Jolada Rotti, Ennegai & Shenga Chutney"),
    SWEET_SNACK_LEGEND("Legendary Sweets & Snacks", "Authentic Mysore Pak, Dharwad Peda & Maddur Vada")
}

enum class PureVegType(val label: String, val badgeColorHex: Long) {
    PURE_VEG_100("100% Pure Vegetarian", 0xFF10B981),
    SATVIK_NO_ONION_GARLIC("Pure Satvik (No Onion / Garlic)", 0xFFF59E0B),
    JAIN_OPTIONS_AVAILABLE("Jain Options Available", 0xFF8B5CF6)
}

data class VegRestaurant(
    val id: String,
    val name: String,
    val kannadaName: String,
    val district: String,
    val highwayOrLocation: String,
    val category: VegCategory,
    val vegType: PureVegType,
    val approxCostPerPersonInr: Int,
    val isFreeTempleAnnadana: Boolean,
    val cleanlinessRating: Double,
    val signatureDishes: List<String>,
    val timings: String,
    val address: String,
    val mapsSearchQuery: String,
    val proTip: String,
    val imageUrl: String,
    val licenseTag: String = "CC BY-SA 4.0 / Wikimedia Commons",
    val authorCredit: String = "Public / Commons Archive"
)

data class TempleAnnadanaGuidance(
    val templeName: String,
    val diningHallName: String,
    val lunchTimings: String,
    val dinnerTimings: String,
    val dressCode: String,
    val costNotes: String,
    val mealDescription: String,
    val etiquetteTip: String
)
