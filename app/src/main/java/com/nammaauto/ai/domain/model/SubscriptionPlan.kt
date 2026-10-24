package com.nammaauto.ai.domain.model

enum class PlanTier(
    val title: String,
    val priceFormatted: String,
    val period: String,
    val badge: String? = null
) {
    DAILY_PASS(
        title = "Bengaluru Day Pass",
        priceFormatted = "₹19",
        period = "24 hours",
        badge = "Quick Trip"
    ),
    MONTHLY_PRO(
        title = "Namma Commuter Pro",
        priceFormatted = "₹79",
        period = "per month",
        badge = "Most Popular"
    ),
    LIFETIME_COMMUTER(
        title = "Bangalore Native VIP",
        priceFormatted = "₹299",
        period = "Lifetime Access",
        badge = "Best Value"
    )
}

data class UserSubscriptionState(
    val isProUser: Boolean = false,
    val activeTier: PlanTier? = null,
    val freeDailyScansRemaining: Int = 3,
    val freeDailyPhrasesRemaining: Int = 5
)
