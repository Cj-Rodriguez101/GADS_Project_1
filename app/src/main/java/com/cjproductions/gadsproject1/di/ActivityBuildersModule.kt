package com.cjproductions.gadsproject1.di

import com.cjproductions.gadsproject1.Activities.LeaderBoardActivity
import com.cjproductions.gadsproject1.Activities.SplashActivity
import com.cjproductions.gadsproject1.Activities.SubmitActivity
import com.cjproductions.gadsproject1.di.Fragments.MainFragmentBuildersModule
import com.cjproductions.gadsproject1.di.ViewModel.LeaderBoardViewModelModule
import com.cjproductions.gadsproject1.di.ViewModel.SubmitViewModelModule
import com.cjproductions.gadsproject1.di.submit.SubmitModule
import com.cjproductions.gadsproject1.di.submit.SubmitScope
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ActivityBuildersModule {


    @ContributesAndroidInjector(
        modules = [LeaderBoardViewModelModule::class, MainFragmentBuildersModule::class]
    )
    abstract fun contributeLeaderBoardActivity(): LeaderBoardActivity

    @SubmitScope
    @ContributesAndroidInjector(
        modules = [SubmitModule::class, SubmitViewModelModule::class]
    )
    abstract fun contributesSubmitActivity(): SubmitActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity
}