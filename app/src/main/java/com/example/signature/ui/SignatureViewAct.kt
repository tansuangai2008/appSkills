package com.example.signature.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActSignatureBinding

/**
 *  author : ly
 *  date : 2020/9/16 13:43
 *  description :手势签名操作
 */
class SignatureViewAct : AppCompatActivity() {
    private lateinit var binding: ActSignatureBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActSignatureBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}