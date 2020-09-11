package com.cjproductions.gadsproject1.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cjproductions.gadsproject1.R
import com.cjproductions.gadsproject1.ViewModel.setSkilledIQList
import com.cjproductions.gadsproject1.ui.Adapters.SkillIQAdapter
import com.cjproductions.gadsproject1.ui.LeaderBoard.State.MainStateEvent
import com.cjproductions.gadsproject1.util.RecyclerViewTopDecoration
import kotlinx.android.synthetic.main.fragment_skill_iq.*


class SkillIQFragment : BaseMainFragment() {

    lateinit var skillIQAdapter: SkillIQAdapter

    private var TAG = "SkillIQFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_iq, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initRecyclerView()
    }

    private fun initRecyclerView(){
        skill_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@SkillIQFragment.context)
            val recyclerViewTopDecoration = RecyclerViewTopDecoration(30)
            addItemDecoration(recyclerViewTopDecoration)
            skillIQAdapter = SkillIQAdapter()
            adapter = skillIQAdapter
        }
    }

    private fun subscribeObservers(){
        //observe the dataState to retrieve result from viewModel -> repository - ...
        leaderBoardViewModel.dataState.observe(viewLifecycleOwner, Observer {dataState->
            dataState?.let {
                stateChangeListener.onDataStateChange(dataState)
                it.data?.let { data ->
                    data.data.let {event ->
                        event?.peekContent()?.skilledIndividualsField.let {skilledIndividualsField ->
                            skilledIndividualsField?.skilledList.let {skilledList->
                                skilledList?.let {
                                    Log.e(TAG, "the list here is $it")
                                    leaderBoardViewModel.setSkilledIQList(skilledList)
                                    event?.hasBeenHandled = true
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
                if(!viewState2.skilledIndividualsField?.skilledList.isNullOrEmpty()){
                    Log.e(TAG, "LIST IS NOT NULL")
                    empty_skilled_list.visibility = View.GONE
                    skill_recycler_view.visibility = View.VISIBLE
                    skillIQAdapter.apply {
                        submitList(viewState2.skilledIndividualsField?.skilledList!!)
                    }
                } else {
                    Log.e(TAG, "LIST IS NULL")
                    empty_skilled_list.visibility = View.VISIBLE
                    skill_recycler_view.visibility = View.GONE
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        leaderBoardViewModel.setStateEvent(MainStateEvent.SkilledIQSearchEvent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        skill_recycler_view?.adapter = null //to prevent memory leaks
    }
}