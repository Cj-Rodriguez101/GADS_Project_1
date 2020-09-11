package com.cjproductions.gadsproject1.util

interface DataStateChangedListener {

    interface DataStateChangeListener{

        fun onDataStateChange(dataState: DataState<*>?)

//        fun expandAppBar()

        fun hideSoftKeyboard()

    }
}