package com.cjproductions.gadsproject1.ui.Submit.State

sealed class SubmitStateEvent {

    data class SendSubmissionEvent(
        var firstName: String,
        var lastName: String,
        var email: String,
        var linkToGitHub: String
    ): SubmitStateEvent()

    class None: SubmitStateEvent()
}