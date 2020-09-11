package com.cjproductions.gadsproject1.ui.LeaderBoard.State

sealed class MainStateEvent {

    object LeaderBoardSearchEvent : MainStateEvent()

    object SkilledIQSearchEvent : MainStateEvent()

    class None: MainStateEvent()

}