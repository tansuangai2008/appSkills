package com.example.nightmode

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.act_change_mode.*

/**
 *  author : ly
 *  date : 2020/10/12 10:38
 *  description : 黑夜模式
 */
class NightModeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_change_mode)
        tv_light_model.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                getWindow().setWindowAnimations(R.style.ani_in_bottm_out_bottom);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        })
        tv_night_model.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                getWindow().setWindowAnimations(R.style.ani_in_bottm_out_bottom);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

        })
    }
}