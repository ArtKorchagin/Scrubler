package com.artkorchagin.scrubler.android

import android.app.Application
import com.artkorchagin.scrubler.common.di.initKoin
import org.koin.android.ext.koin.androidContext

class ScrublerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(applicationContext)
        }
    }
}