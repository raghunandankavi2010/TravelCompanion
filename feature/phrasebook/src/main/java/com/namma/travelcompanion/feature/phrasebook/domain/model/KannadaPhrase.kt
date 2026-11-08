package com.namma.travelcompanion.feature.phrasebook.domain.model

enum class PhraseCategory(val label: String, val iconDescription: String) {
    NO_METER("No-Meter Rides", "App-Rate Matcher"),
    HAILING("Catching Auto", "Booking/Stopping"),
    METER("Meter Request", "Enforcing Lawful Fare"),
    NEGOTIATE("Fair Haggling", "Smart Counter-Offer"),
    DIRECTIONS("Turn & Stop", "Driver Guidance"),
    RAIN_SURGE("Rain & Night", "Special Scenarios"),
    SAFETY("Emergency", "Immediate Assistance"),
    TOURIST_SIGHTSEEING("Tourist & Sights", "Tickets, Guides & Entry"),
    FOOD_DINING("Food & Darshini", "Order Food & 'No Spicy'"),
    HOTEL_SHOPPING("Hotels & Shopping", "Bargaining & Check-In"),
    TRANSIT_BUS("Bus & Intercity", "KSRTC & Metro")
}

data class KannadaPhrase(
    val id: String,
    val category: PhraseCategory,
    val kannadaLipi: String,
    val phoneticEnglish: String,
    val englishMeaning: String,
    val contextTip: String,
    val isProOnly: Boolean = false
)
