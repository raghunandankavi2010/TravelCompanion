package com.namma.travelcompanion.domain.model

/**
 * One-time "Full Access" unlock. Safety features (SOS, helplines, Night Guardian) are never gated.
 * [fullAccessPrice] comes from Play (localised); null until Play answers or when billing is unavailable.
 */
data class UserSubscriptionState(
    val isProUser: Boolean = false,
    val fullAccessPrice: String? = null,
    val isPurchasePending: Boolean = false,
    val freeDailyScansRemaining: Int = 3,
    val freeDailyPhrasesRemaining: Int = 5
)
