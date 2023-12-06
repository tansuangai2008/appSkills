package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.myapplication.databinding.ActConstraintBinding

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

    private lateinit var binding: ActConstraintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActConstraintBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}