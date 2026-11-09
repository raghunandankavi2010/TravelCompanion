package com.namma.travelcompanion.data.billing

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.PendingPurchasesParams
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import com.android.billingclient.api.acknowledgePurchase
import com.android.billingclient.api.queryProductDetails
import com.android.billingclient.api.queryPurchasesAsync
import com.namma.travelcompanion.domain.model.UserSubscriptionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 * Free tier with daily allowances, plus a single non-consumable Play product ([FULL_ACCESS_PRODUCT_ID])
 * that removes them for good. The entitlement is cached in prefs so the app works offline, and re-checked
 * against Play on every connection, which also revokes it after a refund.
 *
 * ponytail: no server-side purchase verification (no backend). Add a verification endpoint if piracy
 * of the unlock ever shows up in revenue numbers.
 */
class BillingManager(context: Context, private val scope: CoroutineScope) {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val _subscriptionState = MutableStateFlow(loadState())
    val subscriptionState: StateFlow<UserSubscriptionState> = _subscriptionState.asStateFlow()

    private var fullAccessDetails: ProductDetails? = null

    private val client: BillingClient = BillingClient.newBuilder(context)
        .setListener { result, purchases -> if (result.isOk) purchases?.let(::handlePurchases) }
        .enablePendingPurchases(PendingPurchasesParams.newBuilder().enableOneTimeProducts().build())
        .enableAutoServiceReconnection()
        .build()

    init {
        client.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(result: BillingResult) {
                if (result.isOk) scope.launch { refreshFromPlay() }
                else Log.w(TAG, "Billing unavailable: ${result.debugMessage}")
            }

            override fun onBillingServiceDisconnected() = Unit // auto-reconnect is enabled
        })
    }

    private suspend fun refreshFromPlay() {
        val details = client.queryProductDetails(
            QueryProductDetailsParams.newBuilder()
                .setProductList(
                    listOf(
                        QueryProductDetailsParams.Product.newBuilder()
                            .setProductId(FULL_ACCESS_PRODUCT_ID)
                            .setProductType(BillingClient.ProductType.INAPP)
                            .build()
                    )
                )
                .build()
        )
        fullAccessDetails = details.productDetailsList?.firstOrNull()
        _subscriptionState.update { it.copy(fullAccessPrice = fullAccessDetails?.oneTimePurchaseOfferDetails?.formattedPrice) }

        val owned = client.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.INAPP).build()
        )
        if (owned.billingResult.isOk) {
            val ownsFullAccess = owned.purchasesList.any { it.isFullAccess && it.purchaseState == Purchase.PurchaseState.PURCHASED }
            if (!ownsFullAccess) setPro(false) // refunded or never bought
            handlePurchases(owned.purchasesList)
        }
    }

    /** @return false when Play has not returned the product yet (offline, no Play Store, not set up in Console). */
    fun launchFullAccessPurchase(activity: Activity): Boolean {
        val details = fullAccessDetails ?: return false
        val params = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(
                listOf(BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(details).build())
            )
            .build()
        return client.launchBillingFlow(activity, params).isOk
    }

    private fun handlePurchases(purchases: List<Purchase>) {
        purchases.filter { it.isFullAccess }.forEach { purchase ->
            when (purchase.purchaseState) {
                Purchase.PurchaseState.PURCHASED -> {
                    setPro(true)
                    // Play refunds purchases that stay unacknowledged for 3 days.
                    if (!purchase.isAcknowledged) scope.launch {
                        client.acknowledgePurchase(
                            AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase.purchaseToken).build()
                        )
                    }
                }
                Purchase.PurchaseState.PENDING -> _subscriptionState.update { it.copy(isPurchasePending = true) }
                else -> Unit
            }
        }
    }

    private fun setPro(isPro: Boolean) {
        prefs.edit { putBoolean(KEY_IS_PRO, isPro) }
        _subscriptionState.update { it.copy(isProUser = isPro, isPurchasePending = it.isPurchasePending && !isPro) }
    }

    private fun loadState(): UserSubscriptionState {
        val today = LocalDate.now().toString()
        if (prefs.getString(KEY_LAST_DATE, "") != today) {
            prefs.edit {
                putString(KEY_LAST_DATE, today)
                putInt(KEY_FREE_SCANS, DEFAULT_FREE_SCANS)
                putInt(KEY_FREE_PHRASES, DEFAULT_FREE_PHRASES)
            }
        }
        return UserSubscriptionState(
            isProUser = prefs.getBoolean(KEY_IS_PRO, false),
            freeDailyScansRemaining = prefs.getInt(KEY_FREE_SCANS, DEFAULT_FREE_SCANS),
            freeDailyPhrasesRemaining = prefs.getInt(KEY_FREE_PHRASES, DEFAULT_FREE_PHRASES)
        )
    }

    fun consumeScanQuota(): Boolean = consume(KEY_FREE_SCANS) { it.freeDailyScansRemaining }

    fun consumePhraseQuota(): Boolean = consume(KEY_FREE_PHRASES) { it.freeDailyPhrasesRemaining }

    private fun consume(key: String, remaining: (UserSubscriptionState) -> Int): Boolean {
        val current = _subscriptionState.value
        // Reloading rolls the allowance over if the day changed while the app was open.
        fun reload() = loadState().copy(fullAccessPrice = current.fullAccessPrice, isPurchasePending = current.isPurchasePending)
        val state = reload().also { _subscriptionState.value = it }
        if (state.isProUser) return true
        val left = remaining(state)
        if (left <= 0) return false
        prefs.edit { putInt(key, left - 1) }
        _subscriptionState.value = reload()
        return true
    }

    private val BillingResult.isOk get() = responseCode == BillingClient.BillingResponseCode.OK
    private val Purchase.isFullAccess get() = FULL_ACCESS_PRODUCT_ID in products

    companion object {
        /** Create this as a one-time product in Play Console → Monetize → In-app products. */
        const val FULL_ACCESS_PRODUCT_ID = "full_access"

        private const val TAG = "BillingManager"
        private const val PREFS_NAME = "namma_auto_billing_prefs"
        private const val KEY_IS_PRO = "is_pro_user"
        private const val KEY_FREE_SCANS = "free_scans"
        private const val KEY_FREE_PHRASES = "free_phrases"
        private const val KEY_LAST_DATE = "last_date"

        const val DEFAULT_FREE_SCANS = 3
        const val DEFAULT_FREE_PHRASES = 5
    }
}
