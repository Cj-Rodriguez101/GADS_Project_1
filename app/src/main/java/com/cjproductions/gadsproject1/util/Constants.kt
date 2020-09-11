package com.cjproductions.gadsproject1.util

class Constants {

    companion object{
        const val BASE_URL = " https://gadsapi.herokuapp.com/"
        const val FORM_URL = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/"
        const val FORM_URL_TESTING = "https://docs.google.com/forms/d/e/1FAIpQLSfNyKEoZoumc0gz-AtsorpX0ngK-cS-CLa62_FdTJ-oR2S1qQ/"
        const val NETWORK_TIMEOUT = 4000L
        const val ERROR_FOR_SUBMISSION = "error for submission"
        const val TESTING_NETWORK_DELAY = 0L // fake network delay for testing
        const val TESTING_CACHE_DELAY = 0L // fake cache delay for testing
        const val SUCCESS_RESPONSE_SUBMIT = "success" // fake cache delay for testing
        const val PLACE_HOLDER = "nothing" // fake cache delay for testing
        const val JSON_DOCUMENT_ERROR = "JSON document was not fully consumed" //was getting errors so had to workAround if you know what I mean

    }
}