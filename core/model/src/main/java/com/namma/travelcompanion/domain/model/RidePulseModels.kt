package com.namma.travelcompanion.domain.model

enum class AcceptanceChance(val label: String, val scoreDescription: String) {
    VERY_HIGH("90%+ Acceptance", "Instant Pickup / High Driver Loyalty"),
    HIGH("75% - 89% Acceptance", "Good Chance / Minor Wait"),
    MODERATE("50% - 74% Acceptance", "May need 1-2 retries or tip"),
    LOW_REJECTION_RISK("< 50% Acceptance", "High Driver Cancellation Risk")
}

data class RideAppOption(
    val id: String,
    val appName: String,
    val fareRupees: Double,
    val etaMinutes: Int,
    val acceptancePercentage: Int,
    val acceptanceChance: AcceptanceChance,
    val recommendedTipRupees: Int,
    val proCommuteInsight: String,
    val packageName: String,
    val deepLinkUri: String?,
    val isBestChoiceOverall: Boolean = false
)

data class MultiAppRadarReport(
    val distanceKm: Double,
    val fromLocation: String,
    val toLocation: String,
    val bestAppRecommendation: RideAppOption,
    val options: List<RideAppOption>,
    val streetAutoAlternativeFare: Double,
    val weatherCondition: String,
    val peakTrafficNotice: String
)
