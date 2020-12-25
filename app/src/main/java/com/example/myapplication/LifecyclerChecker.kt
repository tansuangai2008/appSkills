package com.example.myapplication

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 *  author : ly
 *  date : 2020/12/24 16:55
 *  description : 监听生命周期
 */
open class LifecyclerChecker : LifecycleObserver {
    private val TAG = LifecyclerChecker::class.java.simpleName

    /**
     * 程序在后台
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onAppBackGround() {
        Log.e(TAG, " 程序进入到后台了!")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onAppForeground() {
        Log.e(TAG, " 程序进入到前台了!")
    }


}