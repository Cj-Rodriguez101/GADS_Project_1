package com.cjproductions.gadsproject1.util

/*
For Creating MessageType to be displayed, part of the UICommunicationListener implementation
 */
data class UIMessage(
    val message: String,
    val uiMessageType: UIMessageType
)

sealed class UIMessageType{

    class Toast: UIMessageType()

    class Dialog: UIMessageType()

    class AreYouSureDialog(
        val callback: AreYouSureCallback
    ): UIMessageType()

    class SuccessDialog: UIMessageType()

    class ErrorDialog: UIMessageType()

    class None: UIMessageType()
}