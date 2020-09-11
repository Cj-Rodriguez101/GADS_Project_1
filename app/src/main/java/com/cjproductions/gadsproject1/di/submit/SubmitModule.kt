package com.cjproductions.gadsproject1.di.submit

import com.cjproductions.gadsproject1.Repository.SubmitRepository
import com.cjproductions.gadsproject1.api.SubmitApiService
import com.cjproductions.gadsproject1.util.Constants
import com.cjproductions.gadsproject1.util.LiveDataCallAdapterFactory
import com.cjproductions.gadsproject1.util.session.SessionManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class SubmitModule {

    @SubmitScope
    @Provides
    fun provideSubmitApiService(retrofit: Retrofit): SubmitApiService {
        return retrofit
            .create(SubmitApiService::class.java)
    }

    //created it this way because of Dagger detected multiple instance
    //https://www.codesd.com/item/define-a-dynamic-base-url-using-retrofit-2-0-and-dagger-2.html
    @SubmitScope
    @Provides
    fun provideSecondRetrofit(gsonBuilder: Gson): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.FORM_URL)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder)).build()
    }

    @SubmitScope
    @Provides
    fun provideSubmitRepository(submitApiService: SubmitApiService, sessionManager: SessionManager):
            SubmitRepository{
        return SubmitRepository(submitApiService, sessionManager)
    }
}