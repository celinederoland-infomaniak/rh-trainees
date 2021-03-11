package com.titouan.infomaniakapp

import android.app.Application
import com.titouan.infomaniakapp.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appComponent)
        }
    }
}