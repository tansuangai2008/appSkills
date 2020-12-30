package com.example.viewbind

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.myapplication.BaseActivity
import com.example.myapplication.databinding.ActViewBindBinding

/**
 *  author : ly
 *  date : 2020/12/30 10:22
 *  description :
 */
class ViewBindActivity : BaseActivity(){

    companion object {
        val TAG: String = ViewBindActivity::class.java.simpleName
        fun startAct(context: Context) {
            val intent = Intent()
            intent.setClass(context, ViewBindActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActViewBindBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvShowFirst.text = "你好我是ViewBinding"
    }
}