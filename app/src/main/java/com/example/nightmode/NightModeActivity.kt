package com.example.nightmode

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActChangeModeBinding

/**
 *  author : ly
 *  date : 2020/10/12 10:38
 *  description : 黑夜模式
 */
class NightModeActivity : BaseActivity() {


    private lateinit var binding: ActChangeModeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActChangeModeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvLightModel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                getWindow().setWindowAnimations(R.style.ani_in_bottm_out_bottom);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        })
        binding.tvNightModel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                getWindow().setWindowAnimations(R.style.ani_in_bottm_out_bottom);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

        })
    }
}