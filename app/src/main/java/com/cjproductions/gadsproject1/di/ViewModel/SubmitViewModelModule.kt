package com.cjproductions.gadsproject1.di.ViewModel

import androidx.lifecycle.ViewModel
import com.cjproductions.gadsproject1.ViewModel.SubmitViewModel
import com.cjproductions.gadsproject1.di.ViewModelKey
import com.cjproductions.gadsproject1.di.submit.SubmitScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SubmitViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SubmitViewModel::class)
    abstract fun  bindSubmitViewModel(submitViewModel: SubmitViewModel): ViewModel
}