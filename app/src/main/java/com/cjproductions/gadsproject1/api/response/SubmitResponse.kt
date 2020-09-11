package com.cjproductions.gadsproject1.api.response

import com.cjproductions.gadsproject1.models.LearningLeader
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubmitResponse (

    @SerializedName("name")
    @Expose
    var name: String
)