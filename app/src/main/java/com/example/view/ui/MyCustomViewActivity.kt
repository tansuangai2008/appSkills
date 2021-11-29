package com.example.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.act_my_custom_view.*
import android.widget.SeekBar

import android.widget.LinearLayout
import com.example.view.SeekBarTips


/**
 *  author : ly
 *  date : 2020/9/10 10:28
 *  description : 自定义UI界面
 */
class MyCustomViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_my_custom_view)
        title_tv.text = "Canvas 画图"
        initData()
    }

    private fun initData() {
        val params = tv_indicator.layoutParams as LinearLayout.LayoutParams
        indicator_seek_bar.setOnSeekBarChangeListener(object :
                SeekBarTips.OnIndicatorSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, indicatorOffset: Float) {
                val indicatorText = "$progress%"
                tv_indicator.text = indicatorText
                params.leftMargin = indicatorOffset.toInt()
                tv_indicator.layoutParams = params
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                tv_indicator.visibility = View.VISIBLE
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                tv_indicator.visibility = View.INVISIBLE
            }
        })
    }
}