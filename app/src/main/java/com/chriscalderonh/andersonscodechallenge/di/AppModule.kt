package com.chriscalderonh.andersonscodechallenge.di

import com.chriscalderonh.andersonscodechallenge.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: MainApplication) {
    @Provides
    @Singleton
    fun provideApp() = app
}