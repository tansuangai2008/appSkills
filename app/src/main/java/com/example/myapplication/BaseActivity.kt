package com.example.myapplication

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

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
}