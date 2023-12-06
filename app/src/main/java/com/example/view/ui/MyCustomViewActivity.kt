package com.example.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import android.widget.SeekBar

import android.widget.LinearLayout
import com.example.myapplication.databinding.ActMyCustomViewBinding
import com.example.view.SeekBarTips


/**
 *  author : ly
 *  date : 2020/9/10 10:28
 *  description : 自定义UI界面
 */
class MyCustomViewActivity : AppCompatActivity() {

    private lateinit var actMyCustomViewBinding: ActMyCustomViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actMyCustomViewBinding = ActMyCustomViewBinding.inflate(layoutInflater)
        setContentView(actMyCustomViewBinding.root)
        actMyCustomViewBinding.titleTv.text = "Canvas 画图"
        initData()
    }

    private fun initData() {
        val params = actMyCustomViewBinding.tvIndicator.layoutParams as LinearLayout.LayoutParams
        actMyCustomViewBinding.indicatorSeekBar.setOnSeekBarChangeListener(object :
                SeekBarTips.OnIndicatorSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, indicatorOffset: Float) {
                val indicatorText = "$progress%"
                actMyCustomViewBinding.tvIndicator.text = indicatorText
                params.leftMargin = indicatorOffset.toInt()
                actMyCustomViewBinding.tvIndicator.layoutParams = params
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                actMyCustomViewBinding.tvIndicator.visibility = View.VISIBLE
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                actMyCustomViewBinding.tvIndicator.visibility = View.INVISIBLE
            }
        })
    }
}