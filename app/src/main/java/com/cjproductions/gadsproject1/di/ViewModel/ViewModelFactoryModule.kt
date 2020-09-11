package com.cjproductions.gadsproject1.di.ViewModel

import androidx.lifecycle.ViewModelProvider
import com.cjproductions.gadsproject1.ViewModelFactory.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}