package com.cjproductions.gadsproject1.util.session

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager
@Inject
constructor(
    val application: Application
){

    private val TAG: String = "AppDebug"

    fun isConnectedToTheInternet(): Boolean{
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            val networkCapabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            return networkCapabilities!=null
        } else {
            try{
                return cm.activeNetworkInfo.isConnected
            }catch (e: Exception){
                Log.e(TAG, "isConnectedToTheInternet: ${e.message}")
            }
            return false
        }
    }
}