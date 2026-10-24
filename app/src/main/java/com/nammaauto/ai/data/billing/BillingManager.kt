package com.nammaauto.ai.data.billing

import android.content.Context
import android.content.SharedPreferences
import com.nammaauto.ai.domain.model.PlanTier
import com.nammaauto.ai.domain.model.UserSubscriptionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BillingManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val _subscriptionState = MutableStateFlow(loadState())
    val subscriptionState: StateFlow<UserSubscriptionState> = _subscriptionState.asStateFlow()

    private fun loadState(): UserSubscriptionState {
        val today = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())
        val savedDate = prefs.getString(KEY_LAST_DATE, "") ?: ""

        val isPro = prefs.getBoolean(KEY_IS_PRO, false)
        val tierName = prefs.getString(KEY_PRO_TIER, null)
        val activeTier = tierName?.let { runCatching { PlanTier.valueOf(it) }.getOrNull() }

        var freeScans = prefs.getInt(KEY_FREE_SCANS, DEFAULT_FREE_SCANS)
        var freePhrases = prefs.getInt(KEY_FREE_PHRASES, DEFAULT_FREE_PHRASES)

        // Reset daily allowances on new calendar day
        if (savedDate != today) {
            freeScans = DEFAULT_FREE_SCANS
            freePhrases = DEFAULT_FREE_PHRASES
            prefs.edit()
                .putString(KEY_LAST_DATE, today)
                .putInt(KEY_FREE_SCANS, freeScans)
                .putInt(KEY_FREE_PHRASES, freePhrases)
                .apply()
        }

        return UserSubscriptionState(
            isProUser = isPro,
            activeTier = activeTier,
            freeDailyScansRemaining = freeScans,
            freeDailyPhrasesRemaining = freePhrases
        )
    }

    fun consumeScanQuota(): Boolean {
        if (_subscriptionState.value.isProUser) return true
        val current = _subscriptionState.value.freeDailyScansRemaining
        if (current <= 0) return false

        val updated = current - 1
        prefs.edit().putInt(KEY_FREE_SCANS, updated).apply()
        _subscriptionState.value = _subscriptionState.value.copy(freeDailyScansRemaining = updated)
        return true
    }

    fun consumePhraseQuota(): Boolean {
        if (_subscriptionState.value.isProUser) return true
        val current = _subscriptionState.value.freeDailyPhrasesRemaining
        if (current <= 0) return false

        val updated = current - 1
        prefs.edit().putInt(KEY_FREE_PHRASES, updated).apply()
        _subscriptionState.value = _subscriptionState.value.copy(freeDailyPhrasesRemaining = updated)
        return true
    }

    fun purchasePlan(tier: PlanTier) {
        prefs.edit()
            .putBoolean(KEY_IS_PRO, true)
            .putString(KEY_PRO_TIER, tier.name)
            .apply()

        _subscriptionState.value = _subscriptionState.value.copy(
            isProUser = true,
            activeTier = tier
        )
    }

    fun resetToFreeTierForTesting() {
        prefs.edit()
            .putBoolean(KEY_IS_PRO, false)
            .remove(KEY_PRO_TIER)
            .putInt(KEY_FREE_SCANS, DEFAULT_FREE_SCANS)
            .putInt(KEY_FREE_PHRASES, DEFAULT_FREE_PHRASES)
            .apply()

        _subscriptionState.value = UserSubscriptionState(
            isProUser = false,
            activeTier = null,
            freeDailyScansRemaining = DEFAULT_FREE_SCANS,
            freeDailyPhrasesRemaining = DEFAULT_FREE_PHRASES
        )
    }

    companion object {
        private const val PREFS_NAME = "namma_auto_billing_prefs"
        private const val KEY_IS_PRO = "is_pro_user"
        private const val KEY_PRO_TIER = "pro_tier"
        private const val KEY_FREE_SCANS = "free_scans"
        private const val KEY_FREE_PHRASES = "free_phrases"
        private const val KEY_LAST_DATE = "last_date"

        const val DEFAULT_FREE_SCANS = 3
        const val DEFAULT_FREE_PHRASES = 5
    }
}
