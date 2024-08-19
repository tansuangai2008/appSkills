package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.util.LinkedList

/**
 *  author : ly
 *  date : 2020/10/12 10:33
 *  description :
 */
open abstract class BaseActivity : AppCompatActivity() {
    /**
     * 测量 View, 确定宽高和绘制坐标
     * */
    var measureHoverView: (parent: RecyclerView, hoverView: View) -> Unit = { parent, hoverView ->
        hoverView.apply {
            val params = layoutParams

            val widthSize: Int
            val widthMode: Int
            when (params.width) {
                -1 -> {
                    widthSize = parent.measuredWidth
                    widthMode = View.MeasureSpec.EXACTLY
                }

                else -> {
                    widthSize = parent.measuredWidth
                    widthMode = View.MeasureSpec.AT_MOST
                }
            }

            val heightSize: Int
            val heightMode: Int
            when (params.height) {
                -1 -> {
                    heightSize = parent.measuredWidth
                    heightMode = View.MeasureSpec.EXACTLY
                }

                else -> {
                    heightSize = parent.measuredWidth
                    heightMode = View.MeasureSpec.AT_MOST
                }
            }

            //标准方法1
            measure(
                View.MeasureSpec.makeMeasureSpec(widthSize, widthMode),
                View.MeasureSpec.makeMeasureSpec(heightSize, heightMode)
            )
            //标准方法2
            layout(0, 0, measuredWidth, measuredHeight)

            //标准方法3
            //draw(canvas)
        }
    }

    companion object{
        var sAllActivitys: LinkedList<Activity> = LinkedList()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        sAllActivitys.add(this)
        super.onCreate(savedInstanceState)
    }

    override fun finish() {
        sAllActivitys.remove(this)
        super.finish()
    }

    override fun onPause() {
        if (isFinishing) {
            sAllActivitys.remove(this)
        }
        Log.e("BaseActivity", "= onPause() = sAllActivitys=" + sAllActivitys + "=size="+sAllActivitys.size)
        super.onPause()
    }

    override fun onDestroy() {
        sAllActivitys.remove(this)
        super.onDestroy()
    }

    override fun onResume() {
        Log.e("BaseActivity", "= onResume() sAllActivitys=" + sAllActivitys + "=size="+sAllActivitys.size)
        super.onResume()
    }


    /**
     * 关掉指定的activity
     *
     * @param clazzName
     */
    fun finishForActivity(clazzName: String) {
        for (i in sAllActivitys.size downTo 1) {
            val activity = sAllActivitys[i - 1]
            if (activity is MainActivity) {
                break
            }
            if (activity.javaClass.name == clazzName) {
                activity.finish()
                break
            }
        }
    }

    /**
     * 关闭指定一组 activity
     */
    fun finishForActivity(vararg clazzName: String) {
        for (i in sAllActivitys.size downTo 1) {
            val activity = sAllActivitys[i - 1]
            if (activity is MainActivity) {
                break
            }
            for (k in clazzName.indices) {
                if (activity.javaClass.name == clazzName[k]) {
                    activity.finish()
                }
            }
        }
    }

}