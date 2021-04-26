package com.chriscalderonh.andersonscodechallenge

import android.app.Application
import com.chriscalderonh.andersonscodechallenge.di.AppComponent
import com.chriscalderonh.andersonscodechallenge.di.ContextModule
import com.chriscalderonh.andersonscodechallenge.di.DaggerAppComponent

class MainApplication : Application() {
    private val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = component
        component.inject()
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}