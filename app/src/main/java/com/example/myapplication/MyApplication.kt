package com.example.myapplication

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.example.photoalbum.NimUIKit
import com.facebook.stetho.Stetho
import com.tencent.mmkv.MMKV

/**
 * author : ly
 * date : 2020/9/15 12:01
 * description :
 */
class MyApplication : Application() {
    companion object {
        private val TAG : String  = MyApplication.javaClass.simpleName
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
    }
}