package com.nammaauto.ai.presentation.store

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nammaauto.ai.NammaAutoApplication
import com.nammaauto.ai.domain.model.PlanTier

class ProStoreViewModel(application: Application) : AndroidViewModel(application) {

    private val billingManager = (application as NammaAutoApplication).billingManager
    val subscriptionState = billingManager.subscriptionState

    fun purchaseTier(tier: PlanTier) {
        billingManager.purchasePlan(tier)
    }

    fun resetForTesting() {
        billingManager.resetToFreeTierForTesting()
    }
}
