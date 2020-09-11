package com.cjproductions.gadsproject1.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.cjproductions.gadsproject1.ViewModel.LeaderBoardViewModel
import com.cjproductions.gadsproject1.ViewModelFactory.ViewModelProviderFactory
import com.cjproductions.gadsproject1.util.DataStateChangedListener
import com.cjproductions.gadsproject1.util.UICommunicationListener
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseMainFragment : DaggerFragment(){

    private val TAG = "AppDebug"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var leaderBoardViewModel: LeaderBoardViewModel

    lateinit var stateChangeListener: DataStateChangedListener.DataStateChangeListener

    lateinit var uiCommunicationListener: UICommunicationListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        leaderBoardViewModel = activity?.run {
            ViewModelProvider(this, providerFactory).get(LeaderBoardViewModel::class.java)
        }?: throw Exception("Invalid Activity")

        //cancel all active jobs before a new one starts
        //cancelActiveJobs()

    }

    fun cancelActiveJobs(){
        leaderBoardViewModel.cancelActiveJobs()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            stateChangeListener = context as DataStateChangedListener.DataStateChangeListener
        }catch(e: ClassCastException){
            Log.e(TAG, "$context must implement DataStateChangeListener" )
        }
        try{
            uiCommunicationListener = context as UICommunicationListener
        }catch(e: ClassCastException){
            Log.e(TAG, "$context must implement UICommunicationListener" )
        }
    }
}