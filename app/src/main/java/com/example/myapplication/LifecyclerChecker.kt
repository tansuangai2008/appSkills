package com.example.myapplication

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 *  author : ly
 *  date : 2020/12/24 16:55
 *  description : 监听生命周期
 */
open class LifecyclerChecker : DefaultLifecycleObserver {
    private val TAG = LifecyclerChecker::class.java.simpleName

    /**
     * 程序在后台
     */
//    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//    private fun onAppBackGround() {
//        Log.e(TAG, " 程序进入到后台了!")
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    private fun onAppForeground() {
//        Log.e(TAG, " 程序进入到前台了!")
//    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
    }


    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
    }
}