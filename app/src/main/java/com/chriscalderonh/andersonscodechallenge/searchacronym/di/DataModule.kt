package com.chriscalderonh.andersonscodechallenge.searchacronym.di

import com.chriscalderonh.andersonscodechallenge.searchacronym.data.SearchDataRepository
import com.chriscalderonh.andersonscodechallenge.searchacronym.domain.SearchRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindSearchDataRepository(
        dataRepository: SearchDataRepository
    ): SearchRepository
}