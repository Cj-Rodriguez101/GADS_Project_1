package com.cjproductions.gadsproject1.di.Fragments

import com.cjproductions.gadsproject1.Fragments.LearningLeadersFragment
import com.cjproductions.gadsproject1.Fragments.SkillIQFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainFragment(): LearningLeadersFragment

    @ContributesAndroidInjector
    abstract fun contributeInsertUpdateFragment(): SkillIQFragment

}