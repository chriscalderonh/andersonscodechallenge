package com.chriscalderonh.andersonscodechallenge.di

import com.chriscalderonh.andersonscodechallenge.MainApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: MainApplication)
}