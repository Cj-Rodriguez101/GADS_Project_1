package com.cjproductions.gadsproject1.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import com.cjproductions.gadsproject1.Repository.LeaderBoardRepo
import com.cjproductions.gadsproject1.ui.LeaderBoard.State.MainStateEvent
import com.cjproductions.gadsproject1.ui.LeaderBoard.State.MainStateEvent.*
import com.cjproductions.gadsproject1.ui.LeaderBoard.State.MainViewState
import com.cjproductions.gadsproject1.util.DataState
import com.cjproductions.gadsproject1.util.Loading
import javax.inject.Inject

class LeaderBoardViewModel
@Inject
constructor(
    val leaderBoardRepo: LeaderBoardRepo
): BaseViewModel<MainStateEvent, MainViewState>(){
    override fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>> {
        when(stateEvent){
            is LeaderBoardSearchEvent -> {
                return leaderBoardRepo.getLeaderList()
            }

            is SkilledIQSearchEvent -> {
                val yes = leaderBoardRepo.getSkilledList()
                Log.e(TAG, "${yes.value?.data?.data?.peekContent()?.skilledIndividualsField?.skilledList}")
                return yes
            }

            is None ->{
                return object : LiveData<DataState<MainViewState>>(){
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

    override fun initNewViewState(): MainViewState {
        return MainViewState()
    }

    fun cancelActiveJobs(){
        leaderBoardRepo.cancelActiveJobs() // cancel active jobs
        handlePendingData() // hide progress bar
    }

    fun handlePendingData(){
        setStateEvent(None())
    }

}