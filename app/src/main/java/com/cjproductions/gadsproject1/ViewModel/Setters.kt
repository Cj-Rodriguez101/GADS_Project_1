package com.cjproductions.gadsproject1.ViewModel

import com.cjproductions.gadsproject1.models.LearningLeader
import com.cjproductions.gadsproject1.models.SkilledIndividual
import com.cjproductions.gadsproject1.ui.LeaderBoard.State.MainViewState

/*
Extension functions of the viewModel for setting the data to the viewState
 */

fun LeaderBoardViewModel.setLearningList(learningList: List<LearningLeader>){
    val update = getCurrentViewStateOrNew()
    var learningLeaderField = update.learningLeaderField
    if (learningLeaderField == null){
        learningLeaderField = MainViewState.LearningLeaderField(learningList)
        update.learningLeaderField = learningLeaderField
        setViewState(update)
    } else {
        update.learningLeaderField?.learningList = learningList
        setViewState(update)
    }
}

fun LeaderBoardViewModel.setSkilledIQList(skilledList: List<SkilledIndividual>){
    val update = getCurrentViewStateOrNew()
    var skillIQField = update.skilledIndividualsField
    if (skillIQField == null){
        skillIQField = MainViewState.SkillIQFields(skilledList)
        update.skilledIndividualsField = skillIQField
        setViewState(update)
    } else {
        skillIQField.skilledList = skilledList
        update.skilledIndividualsField = skillIQField
        setViewState(update)
    }
}