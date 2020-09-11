package com.cjproductions.gadsproject1.util

import com.afollestad.materialdialogs.MaterialDialog

interface UICommunicationListener {

    fun onUIMessageReceived(uiMessage: UIMessage): MaterialDialog?
}