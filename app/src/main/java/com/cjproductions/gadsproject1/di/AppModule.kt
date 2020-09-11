package com.cjproductions.gadsproject1.di

import com.cjproductions.gadsproject1.Repository.LeaderBoardRepo
import com.cjproductions.gadsproject1.api.GADSMainService
import com.cjproductions.gadsproject1.util.Constants
import com.cjproductions.gadsproject1.util.LiveDataCallAdapterFactory
import com.cjproductions.gadsproject1.util.session.SessionManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    }

    @Singleton
    @Provides
    fun provideGADSMainService(retrofitBuilder: Retrofit.Builder): GADSMainService {
        return retrofitBuilder
            .build()
            .create(GADSMainService::class.java)


    }

    @Singleton
    @Provides
    fun provideLeaderBoardRepository(
        gadsMainService: GADSMainService,
        sessionManager: SessionManager
    ): LeaderBoardRepo {
        return LeaderBoardRepo(gadsMainService,sessionManager)
    }
}