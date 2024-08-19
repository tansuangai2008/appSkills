package com.example.lanuch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActLanuchCBinding

/**
 *    author : LIU YANG
 *    date   : 2024/8/8
 *    desc   :
 */
class LaunchCAct : BaseActivity(), View.OnClickListener {


    companion object {
        fun startAct(context: Context) {
            val intent = Intent()
            intent.setClass(context, LaunchCAct::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var _binding: ActLanuchCBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActLanuchCBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        _binding.llRoot.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.ll_root) {
            val intent: Intent = Intent(this@LaunchCAct, LaunchAct::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }
    }
}