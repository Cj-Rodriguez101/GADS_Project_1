package com.cjproductions.gadsproject1.api

import androidx.lifecycle.LiveData
import com.cjproductions.gadsproject1.api.response.SubmitResponse
import com.cjproductions.gadsproject1.models.SkilledIndividual
import com.cjproductions.gadsproject1.util.Constants.Companion.EMAIL_FINAL
import com.cjproductions.gadsproject1.util.Constants.Companion.EMAIL_TEST
import com.cjproductions.gadsproject1.util.Constants.Companion.LAST_NAME_FINAL
import com.cjproductions.gadsproject1.util.Constants.Companion.LAST_NAME_TEST
import com.cjproductions.gadsproject1.util.Constants.Companion.LINK_FINAL
import com.cjproductions.gadsproject1.util.Constants.Companion.LINK_TEST
import com.cjproductions.gadsproject1.util.Constants.Companion.NAME_FINAL
import com.cjproductions.gadsproject1.util.Constants.Companion.NAME_TEST
import com.cjproductions.gadsproject1.util.GenericApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface SubmitApiService {

    @POST("formResponse")
    @FormUrlEncoded
    fun sendSubmitResponse(
        @Field(EMAIL_FINAL) email: String,
        @Field(NAME_FINAL) name: String,
        @Field(LAST_NAME_FINAL) lastName: String,
        @Field(LINK_FINAL) linkToProject: String
    ): LiveData<GenericApiResponse<Any>>
}