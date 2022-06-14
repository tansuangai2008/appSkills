package com.example.myapplication

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.logger.Logger
import com.example.myapplication.annotation.AspectAnalyze
import com.example.photoalbum.NimUIKit
import com.facebook.stetho.Stetho
import com.tencent.mmkv.MMKV
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.reflect.MethodSignature

/**
 * author : ly
 * date : 2020/9/15 12:01
 * description :
 */
class MyApplication : Application() {
    companion object {
        private val TAG: String = MyApplication.javaClass.simpleName

        init {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        NimUIKit.init(this)
        val rootDir = MMKV.initialize(this)
        Log.e(TAG, "rootDir = $rootDir")
        ProcessLifecycleOwner.get().lifecycle.addObserver(LifecyclerChecker())
        AspectTrace.setAspectTraceListener(object : AspectTraceListener {
            override fun logger(tag: String?, message: String?) {
                if (message != null) {
                    Log.e(tag, message)
                }
            }

            override fun onAspectAnalyze(joinPoint: ProceedingJoinPoint?, aspectAnalyze: AspectAnalyze?, methodSignature: MethodSignature?, duration: Long) {
                aspectAnalyze?.name?.let { Log.e("onAspectAnalyze", it) }

            }

        })

    }
}