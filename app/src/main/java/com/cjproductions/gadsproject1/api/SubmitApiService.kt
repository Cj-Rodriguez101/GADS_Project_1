package com.cjproductions.gadsproject1.api

import androidx.lifecycle.LiveData
import com.cjproductions.gadsproject1.api.response.SubmitResponse
import com.cjproductions.gadsproject1.models.SkilledIndividual
import com.cjproductions.gadsproject1.util.GenericApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface SubmitApiService {

    @POST("formResponse")
    @FormUrlEncoded
    fun sendSubmitResponse(
        @Field("entry.1824927963") email: String,
        @Field("entry.1877115667") name: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.284483984") linkToProject: String
    ): LiveData<GenericApiResponse<Any>>
}