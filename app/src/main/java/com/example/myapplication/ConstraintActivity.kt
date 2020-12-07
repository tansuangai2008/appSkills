package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 *  author : ly
 *  date : 2020/12/7 10:33
 *  description : 约束布局的使用
 */
class ConstraintActivity : BaseActivity() {

    companion object {
        fun startAct(context: Context) {
            val intent = Intent()
            intent.setClass(context, ConstraintActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_constraint)
    }
}