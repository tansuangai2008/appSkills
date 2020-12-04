package com.example.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.act_my_custom_view.*

/**
 *  author : ly
 *  date : 2020/9/10 10:28
 *  description : 自定义UI界面
 */
class MyCustomViewActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_my_custom_view)
        title_tv.setText("自测页面")
    }
}