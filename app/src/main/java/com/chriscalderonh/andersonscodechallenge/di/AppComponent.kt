package com.chriscalderonh.andersonscodechallenge.di

import android.content.Context
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class])
interface AppComponent {
    fun inject(): Context
}