package com.namma.travelcompanion.presentation.store

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.namma.travelcompanion.data.billing.BillingManager

class ProStoreViewModel(private val billingManager: BillingManager) : ViewModel() {

    val subscriptionState = billingManager.subscriptionState

    /** @return false if Play is unreachable or the product is not available yet. */
    fun buyFullAccess(activity: Activity): Boolean = billingManager.launchFullAccessPurchase(activity)
}
