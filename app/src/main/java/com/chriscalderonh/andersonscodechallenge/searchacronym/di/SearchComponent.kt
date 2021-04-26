package com.chriscalderonh.andersonscodechallenge.searchacronym.di

import com.chriscalderonh.andersonscodechallenge.di.ActivityScope
import com.chriscalderonh.andersonscodechallenge.di.AppComponent
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.SearchFragment
import dagger.Component

@ActivityScope
@Component(
    modules = [DataModule::class, RemoteModule::class, PresentationModule::class], dependencies = [AppComponent::class]
)
interface SearchComponent {

    fun inject(searchFragment: SearchFragment)
}