package com.example.lanuch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActLanchABinding

/**
 *    author : LIU YANG
 *    date   : 2024/8/8
 *    desc   :
 */
class LaunchAct : BaseActivity(), View.OnClickListener {

    companion object {
        const val TAG = "LaunchAct"
        fun startAct(context: Context) {
            val intent = Intent()
            intent.setClass(context, LaunchAct::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var _binding: ActLanchABinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActLanchABinding.inflate(layoutInflater)
        setContentView(_binding.root)
        _binding.llRoot.setOnClickListener(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e(TAG, "onNewIntent ====")
//        finishForActivity(LaunchCAct::class.java.name, LaunchBAct::class.java.name)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.ll_root) {
            LaunchBAct.startAct(this)
        }
    }
}