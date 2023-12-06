package com.example.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentBBinding

/**
 *  author : ly
 *  date : 2020/12/21 13:33
 *  description :
 */
class FragmentB : BaseFragment() {

    private lateinit var binding: FragmentBBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "我是路人乙")
        binding.tvFragmentB.text = "我是路人乙"
    }
}