package com.moin.bingeprime

import android.app.Application
import com.moin.bingeprime.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BingePrimeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BingePrimeApplication)
            modules(appModule)
        }
    }
}