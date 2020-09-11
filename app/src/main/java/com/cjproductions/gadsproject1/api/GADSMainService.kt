package com.cjproductions.gadsproject1.api

import androidx.lifecycle.LiveData
import com.cjproductions.gadsproject1.models.LearningLeader
import com.cjproductions.gadsproject1.models.SkilledIndividual
import com.cjproductions.gadsproject1.util.GenericApiResponse
import retrofit2.http.GET

interface GADSMainService {

    @GET("api/hours")
    fun getLeaderBoardList(
    ): LiveData<GenericApiResponse<List<LearningLeader>>>

    @GET("api/skilliq")
    fun getSkilledIQList(
    ): LiveData<GenericApiResponse<List<SkilledIndividual>>>
}