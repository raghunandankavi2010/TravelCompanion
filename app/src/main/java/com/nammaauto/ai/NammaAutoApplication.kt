package com.nammaauto.ai

import android.app.Application
import com.nammaauto.ai.core.network.di.networkModule
import com.nammaauto.ai.data.billing.BillingManager
import com.nammaauto.ai.data.di.coreDataModule
import com.nammaauto.ai.data.repository.FavoritesManager
import com.nammaauto.ai.feature.dining.di.diningModule
import com.nammaauto.ai.feature.lens.di.lensModule
import com.nammaauto.ai.feature.meter.di.meterModule
import com.nammaauto.ai.feature.phrasebook.di.phrasebookModule
import com.nammaauto.ai.feature.tourism.di.tourismModule
import com.nammaauto.ai.feature.transit.di.transitModule
import com.nammaauto.ai.presentation.favorites.FavoritesViewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { FavoritesViewModel(get()) }
}

class NammaAutoApplication : Application() {

    lateinit var billingManager: BillingManager
        private set

    lateinit var favoritesManager: FavoritesManager
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger()
            androidContext(this@NammaAutoApplication)
            modules(
                appModule,
                coreDataModule,
                networkModule,
                lensModule,
                transitModule,
                diningModule,
                phrasebookModule,
                tourismModule,
                meterModule
            )
        }

        billingManager = get()
        favoritesManager = get()
    }

    companion object {
        lateinit var instance: NammaAutoApplication
            private set
    }
}
