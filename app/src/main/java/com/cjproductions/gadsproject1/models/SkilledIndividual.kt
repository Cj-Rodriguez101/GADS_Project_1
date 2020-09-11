package com.cjproductions.gadsproject1.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SkilledIndividual(

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("score")
    @Expose
    var score: Int,

    @SerializedName("country")
    @Expose
    var country: String,

    @SerializedName("badgeUrl")
    @Expose
    var badgeUrl: String
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SkilledIndividual

        if (name != other.name) return false
        if (score != other.score) return false
        if (country != other.country) return false
        if (badgeUrl != other.badgeUrl) return false

        return true
    }

    override fun toString(): String {
        return "SkilledIndividual(name='$name', score=$score, country='$country', badgeUrl='$badgeUrl')"
    }


}