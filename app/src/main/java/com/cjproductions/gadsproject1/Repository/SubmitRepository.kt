package com.cjproductions.gadsproject1.Repository

import androidx.lifecycle.LiveData
import com.cjproductions.gadsproject1.api.SubmitApiService
import com.cjproductions.gadsproject1.api.response.SubmitResponse
import com.cjproductions.gadsproject1.ui.Submit.State.SubmitViewState
import com.cjproductions.gadsproject1.util.*
import com.cjproductions.gadsproject1.util.Constants.Companion.SUCCESS_RESPONSE_SUBMIT
import com.cjproductions.gadsproject1.util.session.SessionManager
import kotlinx.coroutines.Job
import javax.inject.Inject

class SubmitRepository
@Inject
constructor(
    val submitApiService: SubmitApiService,
    val sessionManager: SessionManager)
    : JobManager("SubmitRepository"){

    private val TAG = "SubmitRepository"

    fun postSubmission(firstName: String, lastName: String, email: String, link: String):
            LiveData<DataState<SubmitViewState>>{
        return object : NetworkBoundResource<Any, Any, SubmitViewState>(
            sessionManager.isConnectedToTheInternet(),
            true, true, false
        ){
            override suspend fun createCacheRequestAndReturn() {
                //ignore
            }

            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<Any>) {
                onCompleteJob(DataState.data(null, Response(SUCCESS_RESPONSE_SUBMIT, ResponseType.None())))
            }

            override fun createCall(): LiveData<GenericApiResponse<Any>> {
                return submitApiService.sendSubmitResponse(email, firstName, lastName, link)
            }

            override fun loadFromCache(): LiveData<SubmitViewState> {
                return AbsentLiveData.create()
            }

            override suspend fun updateLocalDb(cacheObject: Any?) {
                //ignore
            }

            override fun setJob(job: Job) {
                addJob("postSubmission", job)
            }

        }.asLiveData()
    }

}