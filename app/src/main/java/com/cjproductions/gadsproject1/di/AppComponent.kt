package com.cjproductions.gadsproject1.di

import android.app.Application
import com.cjproductions.gadsproject1.BaseApplication
import com.cjproductions.gadsproject1.di.ViewModel.ViewModelFactoryModule
import com.cjproductions.gadsproject1.util.session.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

    @Singleton
    @Component(
        modules = [
            AndroidInjectionModule::class,
            AppModule::class,
            ActivityBuildersModule::class,
            ViewModelFactoryModule::class
        ]
    )

    interface AppComponent : AndroidInjector<BaseApplication> {

        val sessionManager: SessionManager // must add here b/c injecting into abstract class
    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}