package com.nammaauto.ai

import android.app.Application
import com.nammaauto.ai.data.billing.BillingManager
import com.nammaauto.ai.data.repository.FavoritesManager

class NammaAutoApplication : Application() {

    lateinit var billingManager: BillingManager
        private set

    lateinit var favoritesManager: FavoritesManager
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        billingManager = BillingManager(this)
        favoritesManager = FavoritesManager(this)
    }

    companion object {
        lateinit var instance: NammaAutoApplication
            private set
    }
}
