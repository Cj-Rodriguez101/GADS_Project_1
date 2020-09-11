package com.cjproductions.gadsproject1.ViewModel

import androidx.lifecycle.LiveData
import com.cjproductions.gadsproject1.Repository.SubmitRepository
import com.cjproductions.gadsproject1.ui.LeaderBoard.State.MainViewState
import com.cjproductions.gadsproject1.ui.Submit.State.SubmitStateEvent
import com.cjproductions.gadsproject1.ui.Submit.State.SubmitViewState
import com.cjproductions.gadsproject1.util.DataState
import com.cjproductions.gadsproject1.util.Loading
import javax.inject.Inject

class SubmitViewModel
@Inject
constructor(
    val submitRepository: SubmitRepository
): BaseViewModel<SubmitStateEvent, SubmitViewState>(){
    override fun handleStateEvent(stateEvent: SubmitStateEvent): LiveData<DataState<SubmitViewState>> {
        when(stateEvent){

            is SubmitStateEvent.SendSubmissionEvent -> {
                return submitRepository.postSubmission(
                    stateEvent.firstName,
                    stateEvent.lastName,
                    stateEvent.email,
                    stateEvent.linkToGitHub
                )
            }

            is SubmitStateEvent.None -> {
                return object : LiveData<DataState<SubmitViewState>>(){
                    override fun onActive() {
                        super.onActive()
                        value = DataState(
                            null,
                            Loading(false),
                            null
                        )
                    }
                }
            }
        }
    }

    override fun initNewViewState(): SubmitViewState {
        return SubmitViewState()
    }

    private fun cancelActiveJobs(){
        handlePendingData()
        submitRepository.cancelActiveJobs()
    }

    private fun handlePendingData(){
        setStateEvent(SubmitStateEvent.None())
    }

    override fun onCleared() {
        super.onCleared()
        cancelActiveJobs()
    }

}