package com.cjproductions.gadsproject1.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cjproductions.gadsproject1.R
import com.cjproductions.gadsproject1.ViewModel.setLearningList
import com.cjproductions.gadsproject1.ui.Adapters.LearningLeaderAdapter
import com.cjproductions.gadsproject1.ui.LeaderBoard.State.MainStateEvent
import com.cjproductions.gadsproject1.util.RecyclerViewTopDecoration
import kotlinx.android.synthetic.main.fragment_learning_leaders.*


class LearningLeadersFragment : BaseMainFragment() {

    lateinit var learningLeaderAdapter: LearningLeaderAdapter

    private val TAG = "LearningLeadersFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning_leaders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initRecyclerView()
    }

    private fun initRecyclerView(){
        learning_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@LearningLeadersFragment.context)
            val recyclerViewTopDecoration = RecyclerViewTopDecoration(30)
            addItemDecoration(recyclerViewTopDecoration)
            learningLeaderAdapter = LearningLeaderAdapter()
            adapter = learningLeaderAdapter
        }
    }

    private fun subscribeObservers(){
        //observe the dataState to retrieve result from viewModel -> repository - ...
        leaderBoardViewModel.dataState.observe(viewLifecycleOwner, Observer {dataState->
            dataState?.let {
                stateChangeListener.onDataStateChange(dataState)
                it.data?.let { data ->
                    data.data?.let {event ->
                        //to retrieve the data only once, after retrieval do not look in the
                        // viewModel anymore
                        event.peekContent().learningLeaderField.let {learningLeaderField ->
                            learningLeaderField?.learningList.let {learningList->
                                learningList?.let {
                                    leaderBoardViewModel.setLearningList(learningList)
                                }
                            }
                        }
                    }
                }
            }
        })
        //observe the viewState to set the data to the views accordingly
        leaderBoardViewModel.viewState.observe(viewLifecycleOwner, Observer { viewState->
            viewState?.let { viewState2->
                if(!viewState2.learningLeaderField?.learningList.isNullOrEmpty()){
                    empty_learning_list.visibility = View.GONE
                    learning_recycler_view.visibility = View.VISIBLE
                    learningLeaderAdapter.apply {
                        submitList(viewState2.learningLeaderField?.learningList!!)
                    }
                } else {
                    empty_learning_list.visibility = View.VISIBLE
                    learning_recycler_view.visibility = View.GONE
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        leaderBoardViewModel.setStateEvent(MainStateEvent.LeaderBoardSearchEvent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        learning_recycler_view?.adapter = null //to prevent memory leaks
    }

}