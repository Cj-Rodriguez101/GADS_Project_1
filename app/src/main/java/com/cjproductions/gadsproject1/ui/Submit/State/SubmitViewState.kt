package com.cjproductions.gadsproject1.ui.Submit.State

data class SubmitViewState(
    var submitFields: SubmitFields? = SubmitFields()
){

    data class SubmitFields(
        var firstName: String? = null,
        var lastName: String? = null,
        var email: String? = null,
        var gitbuhLink: String? = null
    )
}