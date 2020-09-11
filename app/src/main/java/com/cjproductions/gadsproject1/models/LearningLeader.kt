package com.cjproductions.gadsproject1.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LearningLeader(

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("hours")
    @Expose
    var hours: Int,

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

        other as LearningLeader

        if (name != other.name) return false
        if (hours != other.hours) return false
        if (country != other.country) return false
        if (badgeUrl != other.badgeUrl) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + hours
        result = 31 * result + country.hashCode()
        result = 31 * result + badgeUrl.hashCode()
        return result
    }

    override fun toString(): String {
        return "LearningLeader(name='$name', hours=$hours, country='$country', badgeUrl='$badgeUrl')"
    }


}