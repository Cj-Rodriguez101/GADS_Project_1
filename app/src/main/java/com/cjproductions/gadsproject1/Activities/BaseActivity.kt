package com.cjproductions.gadsproject1.Activities

import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.afollestad.materialdialogs.MaterialDialog
import com.cjproductions.gadsproject1.util.*
import com.cjproductions.gadsproject1.util.DataStateChangedListener.DataStateChangeListener
import com.cjproductions.gadsproject1.util.ErrorHandling.Companion.UNABLE_TODO_OPERATION_WO_INTERNET
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/*
Superclass of all activities, contain methods for interacting with the UI
 */

@Suppress("UNREACHABLE_CODE")
abstract class BaseActivity: DaggerAppCompatActivity(),
    DataStateChangeListener, UICommunicationListener
    {

        private val TAG = "BaseActivity"

        //reacts to DataState received from the viewModel
        override fun onDataStateChange(dataState: DataState<*>?) {
            dataState?.let{
                GlobalScope.launch(Dispatchers.Main){
                    displayProgressBar(it.loading.isLoading)

                    it.error?.let { errorEvent ->
                        handleStateError(errorEvent)
                    }

                    it.data?.let {
                        it.response?.let { responseEvent ->
                            handleStateResponse(responseEvent)
                        }
                    }
                }
            }
        }

        abstract fun displayProgressBar(bool: Boolean)


        private fun handleStateResponse(event: Event<Response>){
            event.getContentIfNotHandled()?.let{

                when(it.responseType){
                    is ResponseType.Toast ->{
                        it.message?.let{message ->
                            displayToast(message)
                        }
                    }

                    is ResponseType.Dialog ->{
                        it.message?.let{ message ->
                            displayCustomSuccessDialog(message)
                        }
                    }

                    is ResponseType.None -> {
                        Log.i(TAG, "handleStateResponse: ${it.message}")
                    }
                }

            }
        }

        private fun handleStateError(event: Event<StateError>){
            event.getContentIfNotHandled()?.let{
                when(it.response.responseType){
                    is ResponseType.Toast ->{
                        it.response.message?.let{message ->
                            displayToast(message)
                        }
                    }

                    is ResponseType.Dialog ->{
                        it.response.message?.let{ message ->
                            if (message == UNABLE_TODO_OPERATION_WO_INTERNET){
                                displayToast(message)
                                displayCustomErrorDialog(message)
                            } else {
                                displayCustomErrorDialog(message)
                            }

                        }
                    }

                    is ResponseType.None -> {
                        Log.i(TAG, "handleStateError: ${it.response.message}")
                    }

                }
            }
        }

        override fun hideSoftKeyboard() {
            if (currentFocus != null) {
                val inputMethodManager = getSystemService(
                    Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager
                    .hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
        }

    override fun onUIMessageReceived(uiMessage: UIMessage): MaterialDialog? {
        return when(uiMessage.uiMessageType){

            is UIMessageType.AreYouSureDialog -> {
                return areYouSureCustomDialog(
                    uiMessage.uiMessageType.callback
                )
            }

            is UIMessageType.Toast -> {
                displayToast(uiMessage.message)
                return null
            }

            is UIMessageType.Dialog -> {
                displayInfoDialog(uiMessage.message)
                return null
            }

            is UIMessageType.SuccessDialog -> {
                return displayCustomSuccessDialog(uiMessage.message)
            }

            is UIMessageType.ErrorDialog -> {
                return displayCustomErrorDialog(uiMessage.message)
            }

            is UIMessageType.None -> {
                Log.i(TAG, "onUIMessageReceived: ${uiMessage.message}")
                return null
            }

        }
    }

}