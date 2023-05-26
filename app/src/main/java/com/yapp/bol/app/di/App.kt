package com.yapp.bol.app.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        private lateinit var application: App
        fun getInstance(): App = application
    }
}
