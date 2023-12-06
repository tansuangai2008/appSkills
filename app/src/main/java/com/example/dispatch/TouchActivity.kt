package com.example.dispatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.myapplication.BaseActivity
import com.example.myapplication.ConstraintActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActTouchInfoBinding

/**
 *  author : ly
 *  date : 2020/12/7 21:09
 *  description : 触摸事件转发
 */
class TouchActivity : BaseActivity() {

    companion object {
        private val TAG = TouchActivity::class.java.simpleName
        fun startAct(context: Context) {
            val intent = Intent()
            intent.setClass(context, TouchActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActTouchInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActTouchInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.llAtouchviewgroup.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.e(TAG, "想要干嘛呢!!!!")
            }

        })
    }
}