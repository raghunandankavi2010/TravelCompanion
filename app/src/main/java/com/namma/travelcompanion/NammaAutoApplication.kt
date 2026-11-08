package com.namma.travelcompanion

import android.app.Application
import com.namma.travelcompanion.core.network.di.networkModule
import com.namma.travelcompanion.data.billing.BillingManager
import com.namma.travelcompanion.data.di.coreDataModule
import com.namma.travelcompanion.data.repository.FavoritesManager
import com.namma.travelcompanion.feature.dining.di.diningModule
import com.namma.travelcompanion.feature.lens.di.lensModule
import com.namma.travelcompanion.feature.meter.di.meterModule
import com.namma.travelcompanion.feature.phrasebook.di.phrasebookModule
import com.namma.travelcompanion.feature.tourism.di.tourismModule
import com.namma.travelcompanion.feature.transit.di.transitModule
import com.namma.travelcompanion.presentation.favorites.FavoritesViewModel
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
