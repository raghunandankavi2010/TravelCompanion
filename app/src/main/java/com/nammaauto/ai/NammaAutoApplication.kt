package com.nammaauto.ai

import android.app.Application
import com.nammaauto.ai.data.billing.BillingManager

class NammaAutoApplication : Application() {

    lateinit var billingManager: BillingManager
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        billingManager = BillingManager(this)
    }

    companion object {
        lateinit var instance: NammaAutoApplication
            private set
    }
}
