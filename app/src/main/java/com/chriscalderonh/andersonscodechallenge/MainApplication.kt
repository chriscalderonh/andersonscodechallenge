package com.chriscalderonh.andersonscodechallenge

import android.app.Application
import com.chriscalderonh.andersonscodechallenge.di.AppComponent
import com.chriscalderonh.andersonscodechallenge.di.AppModule
import com.chriscalderonh.andersonscodechallenge.di.DaggerAppComponent

class MainApplication : Application() {
    private val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}