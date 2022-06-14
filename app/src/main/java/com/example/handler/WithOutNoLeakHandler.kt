package com.example.handler

import android.app.Activity
import android.os.Handler
import android.os.Message
import java.lang.ref.WeakReference

/**
 *    author : liuyang
 *    date   : 2022/1/20
 *    desc   :
 *
 */
class WithOutNoLeakHandler(activity: Activity) : Handler() {
    private val mActivity: WeakReference<Activity> = WeakReference(activity)

    private companion object {
        const val one: Int = 0x1
        const val second: Int = 0x2
        const val three: Int = 0x3
    }

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        if (mActivity.get() == null) {
            return
        }
        when (msg.what) {
            one -> {

            }
            second -> run {

            }
        }
    }

}