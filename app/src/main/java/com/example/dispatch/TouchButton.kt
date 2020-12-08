package com.example.dispatch

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton

/**
 *  author : ly
 *  date : 2020/12/7 22:09
 *  description : View
 */
class TouchButton : AppCompatButton {
    private val TAG = TouchButton::class.java.simpleName

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.action.let {
            when (it) {
                MotionEvent.ACTION_DOWN -> {
                    Log.e(TAG, "===ACTION_DOWN====" + event?.action)
                }
                MotionEvent.ACTION_MOVE -> {
                    Log.e(TAG, "===ACTION_MOVE===" + event?.action)

                }
                MotionEvent.ACTION_UP -> {
                    Log.e(TAG, "==ACTION_UP==" + event?.action)
                }

                else -> {

                }
            }
        }
        return super.onTouchEvent(event)
    }

}