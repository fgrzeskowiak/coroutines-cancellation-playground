package com.netguru.coroutinescancellationplayground

import android.app.Application
import com.netguru.coroutinescancellationplayground.di.featureModule
import com.netguru.coroutinescancellationplayground.di.networkModule
import com.netguru.coroutinescancellationplayground.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    featureModule
                )
            )
        }
    }
}
