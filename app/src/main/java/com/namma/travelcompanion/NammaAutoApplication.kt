package com.namma.travelcompanion

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import com.namma.travelcompanion.data.billing.BillingManager
import com.namma.travelcompanion.di.appModules
import okhttp3.OkHttpClient
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NammaAutoApplication : Application(), SingletonImageLoader.Factory {

    private val billingManager: BillingManager by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NammaAutoApplication)
            modules(appModules)
        }

        billingManager // connect to Play early so the price and ownership are ready
    }

    /** Wikimedia answers 403 to OkHttp's default User-Agent, so every Commons photo would be blank without this. */
    override fun newImageLoader(context: PlatformContext): ImageLoader {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().header("User-Agent", USER_AGENT).build())
            }
            .build()
        return ImageLoader.Builder(context)
            .components { add(OkHttpNetworkFetcherFactory(callFactory = { client })) }
            .build()
    }

    private companion object {
        const val USER_AGENT = "NammaTravelCompanion/${BuildConfig.VERSION_NAME} (Android; raghunandan2005@gmail.com)"
    }
}
