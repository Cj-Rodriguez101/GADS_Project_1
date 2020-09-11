package com.cjproductions.gadsproject1.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cjproductions.gadsproject1.R
import com.cjproductions.gadsproject1.ViewModel.SubmitViewModel
import com.cjproductions.gadsproject1.ViewModelFactory.ViewModelProviderFactory
import com.cjproductions.gadsproject1.ui.Submit.State.SubmitStateEvent
import com.cjproductions.gadsproject1.util.*
import com.cjproductions.gadsproject1.util.Constants.Companion.SUCCESS_RESPONSE_SUBMIT
import kotlinx.android.synthetic.main.activity_submit.*
import kotlinx.android.synthetic.main.cover_with_grey.*
import kotlinx.android.synthetic.main.submit_tool_bar.tool_bar2
import javax.inject.Inject

class SubmitActivity : BaseActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var submitViewModel: SubmitViewModel

    private lateinit var toolbar: Toolbar

    private lateinit var submitButton: Button

    override fun displayProgressBar(bool: Boolean) {
        if(bool){
            //display the two different progress bars
            if (checkIfGreyDisplayed()){
                progress_bar3.visibility = View.VISIBLE
            } else {
                progress_bar2.visibility = View.VISIBLE
            }
        }
        else{
            if (checkIfGreyDisplayed()){
                progress_bar3.visibility = View.GONE
            } else {
                progress_bar2.visibility = View.GONE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        setupActionBar(tool_bar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        submitViewModel = ViewModelProvider(this, providerFactory).get(SubmitViewModel::class.java)
        subscribeObservers()
        submitButton = findViewById(R.id.submit_activity_button)
        submitButton.setOnClickListener {
            overLayGreyView()
            createAreYouSureDialog()
        }
        Log.e("SUBMIT_ACTIVITY", "$submitViewModel")
    }

    private fun overLayGreyView() {
        val viewGrey = layoutInflater.inflate(R.layout.cover_with_grey, null)
        addContentView(
            viewGrey, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        setupActionBar(tool_bar3)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun subscribeObservers() {
        submitViewModel.dataState.observe(this, Observer { dataState->
            dataState?.let {
                onDataStateChange(it)
                dataState.data?.response?.peekContent()?.let {response->
                    if (response.message.equals(SUCCESS_RESPONSE_SUBMIT)){
                        //revert the changes
                        val successDialog = onUIMessageReceived(UIMessage("nothing",
                            UIMessageType.SuccessDialog()))
                        successDialog?.setOnDismissListener {
                            revertChangesToView()
                        }
                    } else {
                        createErrorDialog()
                    }

                    dataState.error?.peekContent()?.response?.message?.let {
                        createErrorDialog()
                    }
                }
            }
        })

        submitViewModel.viewState.observe(this, Observer {viewState->
            viewState?.submitFields.let {submitFields->
                submitFields?.let {
                    it.firstName?.let { submit_first_name.setText(it) }
                    it.lastName?.let { submit_last_name.setText(it) }
                    it.email?.let { submit_email.setText(it) }
                    it.gitbuhLink?.let { submit_github_link.setText(it) }
                }
            }
        })
    }

    private fun createErrorDialog() {
        val errorDialog = onUIMessageReceived(
            UIMessage(
                "nothing",
                UIMessageType.ErrorDialog()
            )
        )
        errorDialog?.setOnDismissListener {
            revertChangesToView()
        }
    }

    private fun revertChangesToView() {
        val rootView = findViewById<ViewGroup>(android.R.id.content)
        val viewGrey = rootView?.getChildAt(1)
        if (viewGrey != null) {
            rootView.removeView(viewGrey)
            setupActionBar(tool_bar2)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun checkIfGreyDisplayed(): Boolean{
        val rootView = findViewById<ViewGroup>(android.R.id.content)
        val viewGrey = rootView?.getChildAt(1)
        return viewGrey != null
    }

    private fun createAreYouSureDialog(){
        val callback = object : AreYouSureCallback{
            override fun proceed() {
                //submitFormData()
                Log.e("SubmitActivity", "button is pressed")
                //make network request
                submitFormData()
            }

            override fun cancel() {
                revertChangesToView()
            }
        }
        onUIMessageReceived(UIMessage("nothing",
            UIMessageType.AreYouSureDialog(callback)))

    }

    //set actionBar to Toolbar here because it is defined the activity not fragment
    private fun setupActionBar(toolbar: Toolbar){
        setSupportActionBar(toolbar)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun submitFormData(){
        submitViewModel.setStateEvent(SubmitStateEvent.SendSubmissionEvent(
            submit_first_name.text.toString(),
            submit_last_name.text.toString(),
            submit_email.text.toString(),
            submit_github_link.text.toString()
        ))
    }
}