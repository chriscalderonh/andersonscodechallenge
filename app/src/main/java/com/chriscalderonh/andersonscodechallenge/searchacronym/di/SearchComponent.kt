package com.chriscalderonh.andersonscodechallenge.searchacronym.di

import com.chriscalderonh.andersonscodechallenge.di.ActivityScope
import dagger.Component

@ActivityScope
@Component(
    modules = [DataModule::class, RemoteModule::class]
)
interface SearchComponent {

}