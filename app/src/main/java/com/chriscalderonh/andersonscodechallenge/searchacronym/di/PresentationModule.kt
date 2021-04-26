package com.chriscalderonh.andersonscodechallenge.searchacronym.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chriscalderonh.andersonscodechallenge.common.ui.execution.AppExecutionThread
import com.chriscalderonh.andersonscodechallenge.common.ui.execution.ExecutionThread
import com.chriscalderonh.andersonscodechallenge.di.ViewModelFactory
import com.chriscalderonh.andersonscodechallenge.di.ViewModelKey
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindExecutionThread(executionThread: AppExecutionThread): ExecutionThread

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(
        viewModel: SearchViewModel): ViewModel
}