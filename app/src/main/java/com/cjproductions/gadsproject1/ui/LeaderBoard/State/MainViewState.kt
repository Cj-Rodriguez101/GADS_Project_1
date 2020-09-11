package com.cjproductions.gadsproject1.ui.LeaderBoard.State

import com.cjproductions.gadsproject1.models.LearningLeader
import com.cjproductions.gadsproject1.models.SkilledIndividual

data class MainViewState(
    var learningLeaderField: LearningLeaderField? = LearningLeaderField(),
    var skilledIndividualsField: SkillIQFields? = SkillIQFields()
){
    data class LearningLeaderField(
        var learningList: List<LearningLeader>? = ArrayList()
    )

    data class SkillIQFields(
        var skilledList: List<SkilledIndividual>? = ArrayList()
    )
}