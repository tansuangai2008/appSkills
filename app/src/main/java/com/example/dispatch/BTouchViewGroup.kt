package com.example.dispatch

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.InputDevice
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 *  author : ly
 *  date : 2020/12/7 21:19
 *  description :
 */
class BTouchViewGroup : LinearLayout {

    private companion object {
        val TAG = BTouchViewGroup::class.java.simpleName
    }

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        ev?.action.let {
            when (it) {
                MotionEvent.ACTION_DOWN -> {
                    Log.e(TAG, "===dispatchTouchEvent ACTION_DOWN====" + ev?.action)
                }
                MotionEvent.ACTION_MOVE -> {
                    Log.e(TAG, "===dispatchTouchEvent ACTION_MOVE===" + ev?.action)

                }
                MotionEvent.ACTION_UP -> {
                    Log.e(TAG, "==dispatchTouchEvent ACTION_UP==" + ev?.action)
                }

                else -> {

                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.action.let {
            when (it) {
                MotionEvent.ACTION_DOWN -> {
                    Log.e(TAG, "===onTouchEvent ACTION_DOWN====" + event?.action)
                }
                MotionEvent.ACTION_MOVE -> {
                    Log.e(TAG, "===onTouchEvent ACTION_MOVE===" + event?.action)

                }
                MotionEvent.ACTION_UP -> {
                    Log.e(TAG, "==onTouchEvent ACTION_UP==" + event?.action)
                }

                else -> {

                }
            }
        }
        return super.onTouchEvent(event)
    }

}