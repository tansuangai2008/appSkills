package com.example.lanuch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActLanchABinding
import com.example.myapplication.databinding.ActLanuchBBinding

/**
 *    author : LIU YANG
 *    date   : 2024/8/8
 *    desc   :
 */
class LaunchBAct: BaseActivity(), View.OnClickListener {

    companion object {
        fun startAct(context: Context) {
            val intent = Intent()
            intent.setClass(context, LaunchBAct::class.java)
            context.startActivity(intent)
        }
    }


    private lateinit var _binding: ActLanuchBBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActLanuchBBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        _binding.llRoot.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.ll_root){
            LaunchCAct.startAct(this)
        }
    }
}