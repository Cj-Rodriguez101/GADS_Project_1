package com.cjproductions.gadsproject1.di.ViewModel

import androidx.lifecycle.ViewModel
import com.cjproductions.gadsproject1.ViewModel.LeaderBoardViewModel
import com.cjproductions.gadsproject1.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LeaderBoardViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LeaderBoardViewModel::class)
    abstract fun bindSparesViewModel(sparesViewModel: LeaderBoardViewModel): ViewModel
}