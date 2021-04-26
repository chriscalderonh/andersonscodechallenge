package com.chriscalderonh.andersonscodechallenge.searchacronym.di

import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.SearchApiFactory
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.SearchRemoteImpl
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.remote.SearchRestApi
import com.chriscalderonh.andersonscodechallenge.searchacronym.data.repository.SearchRemote
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface RemoteModule {
    @Binds
    abstract fun bindSearchRemote(searchRemoteImpl: SearchRemoteImpl): SearchRemote

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideSearchRestApi(): SearchRestApi {
            return SearchApiFactory().makeRestApi()
        }
    }
}