package com.cjproductions.gadsproject1.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.cjproductions.gadsproject1.api.GADSMainService
import com.cjproductions.gadsproject1.models.LearningLeader
import com.cjproductions.gadsproject1.models.SkilledIndividual
import com.cjproductions.gadsproject1.ui.LeaderBoard.State.MainViewState
import com.cjproductions.gadsproject1.ui.LeaderBoard.State.MainViewState.LearningLeaderField
import com.cjproductions.gadsproject1.util.*
import com.cjproductions.gadsproject1.util.session.SessionManager
import kotlinx.coroutines.Job
import javax.inject.Inject

class LeaderBoardRepo
@Inject
constructor(val gadsMainService: GADSMainService,
val sessionManager: SessionManager)
    :JobManager("LeaderBoardRepo"){

    private val TAG = "leaderBoardRepository"

    fun getLeaderList(): LiveData<DataState<MainViewState>>{

        return object : NetworkBoundResource<List<LearningLeader>, Any, MainViewState>(
            sessionManager.isConnectedToTheInternet(),
            true,
            true,
            false
        ){
            override suspend fun createCacheRequestAndReturn() {
                //ignore not used since caching layer is not implemented for this project
            }

            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<List<LearningLeader>>) {
                val leaderList = response.body
                Log.e(TAG, "response is $leaderList")
                onCompleteJob(DataState.data(
                    MainViewState(
                        learningLeaderField =
                        LearningLeaderField(leaderList)
                    ), null))
            }

            override fun createCall(): LiveData<GenericApiResponse<List<LearningLeader>>> {
                return gadsMainService.getLeaderBoardList()
            }

            override fun loadFromCache(): LiveData<MainViewState> {
                return AbsentLiveData.create()
            }

            override suspend fun updateLocalDb(cacheObject: Any?) {
                //ignore
            }

            override fun setJob(job: Job) {
                addJob("getLeaderList", job)
            }

        }.asLiveData()
    }

    fun getSkilledList(): LiveData<DataState<MainViewState>>{

        return object : NetworkBoundResource<List<SkilledIndividual>, Any, MainViewState>(
            sessionManager.isConnectedToTheInternet(),
            true,
            true,
            false
        ){
            override suspend fun createCacheRequestAndReturn() {
                //ignore not used since caching layer is not implemented for this project
            }

            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<List<SkilledIndividual>>) {
                val skilledList = response.body
                Log.e(TAG, "response is $skilledList")
                onCompleteJob(DataState.data(
                    MainViewState(
                        null,
                        MainViewState.SkillIQFields(
                            skilledList
                        )
                    ), null))

            }

            override fun createCall(): LiveData<GenericApiResponse<List<SkilledIndividual>>> {
                return gadsMainService.getSkilledIQList()
            }

            override fun loadFromCache(): LiveData<MainViewState> {
                return AbsentLiveData.create()
            }

            override suspend fun updateLocalDb(cacheObject: Any?) {
                //ignore
            }

            override fun setJob(job: Job) {
                addJob("getSkilledList", job)
            }

        }.asLiveData()
    }

}