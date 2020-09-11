package com.cjproductions.gadsproject1.util

import android.app.Activity
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.list.customListAdapter
import com.cjproductions.gadsproject1.R
import com.google.android.material.snackbar.Snackbar

fun Activity.displayToast(@StringRes message:Int){
    Toast.makeText(this,message, Toast.LENGTH_LONG).show()
}

fun Activity.displayToast(message:String){
    Toast.makeText(this,message, Toast.LENGTH_LONG).show()
}

fun Activity.displaySuccessDialog(message: String?){
    MaterialDialog(this)
        .show{
            title(R.string.text_success)
            message(text = message)
            positiveButton(R.string.text_ok)
        }
}

fun Activity.displayErrorDialog(errorMessage: String?) :MaterialDialog{
    return MaterialDialog(this)
        .show{
            title(R.string.text_error)
            message(text = errorMessage)
            positiveButton(R.string.text_ok)
        }
}

fun Activity.displayInfoDialog(message: String?){
    MaterialDialog(this)
        .show{
            title(R.string.text_info)
            message(text = message)
            positiveButton(R.string.text_ok)
        }
}

fun Activity.areYouSureDialog(message: String, callback: AreYouSureCallback){
    MaterialDialog(this)
        .show{
            title(R.string.are_you_sure)
            message(text = message)
            negativeButton(R.string.text_cancel){
                callback.cancel()
            }
            positiveButton(R.string.text_yes){
                callback.proceed()
            }
        }
}

fun Activity.areYouSureCustomDialog(callback: AreYouSureCallback): MaterialDialog{
    val dialogInView = MaterialDialog(this).show {
        cornerRadius(8F)
        customView(R.layout.custom_are_you_sure)
        //traverse through the hierarchy and detect clicks on the views
        val constraintLayout = this.getCustomView() as ViewGroup
        val cancelButton = constraintLayout.getChildAt(0) as Button
        val yesButton = constraintLayout.getChildAt(3) as Button
        yesButton.setOnClickListener {
            callback.proceed()
            dismiss()
        }
        cancelable(false)
//        onDismiss {dialog->
//            dialog = null
//        }
        cancelButton.setOnClickListener {
            callback.cancel()
            this.dismiss() }

    }

    return dialogInView
}

fun Activity.displayCustomSuccessDialog(message: String?): MaterialDialog{
    return MaterialDialog(this)
        .show{
            cornerRadius(8F)
            customView(R.layout.custom_accept_dialog)
        }
}

fun Activity.displayCustomErrorDialog(errorMessage: String?): MaterialDialog{
    return MaterialDialog(this)
        .show{
            cornerRadius(8F)
            customView(R.layout.custom_reject_dialog)
        }
}


interface AreYouSureCallback {

    fun proceed()

    fun cancel()
}