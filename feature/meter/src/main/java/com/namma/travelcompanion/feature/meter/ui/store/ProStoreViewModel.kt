package com.namma.travelcompanion.feature.meter.ui.store

import androidx.lifecycle.ViewModel
import com.namma.travelcompanion.data.billing.BillingManager
import com.namma.travelcompanion.domain.model.PlanTier

class ProStoreViewModel(private val billingManager: BillingManager) : ViewModel() {

    val subscriptionState = billingManager.subscriptionState

    fun purchaseTier(tier: PlanTier) {
        billingManager.purchasePlan(tier)
    }

    fun resetForTesting() {
        billingManager.resetToFreeTierForTesting()
    }
}
