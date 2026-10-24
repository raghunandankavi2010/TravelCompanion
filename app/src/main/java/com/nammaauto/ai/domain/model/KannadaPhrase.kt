package com.nammaauto.ai.domain.model

enum class PhraseCategory(val label: String, val iconDescription: String) {
    HAILING("Catching Auto", "Booking/Stopping"),
    METER("Meter Request", "Enforcing Lawful Fare"),
    NEGOTIATE("Fair Haggling", "Smart Counter-Offer"),
    DIRECTIONS("Turn & Stop", "Driver Guidance"),
    RAIN_SURGE("Rain & Night", "Special Scenarios"),
    SAFETY("Emergency", "Immediate Assistance")
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
