package com.example.coroutines

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActCoroutinesBinding
import kotlinx.coroutines.*

/**
 *  author : ly
 *  date : 2020/12/9 17:20
 *  description : 协程试炼
 */
class CoroutinesActivity : BaseActivity() {
    companion object {
        val TAG: String = CoroutinesActivity::class.java.simpleName
        fun startAct(context: Context) {
            val intent = Intent()
            intent.setClass(context, CoroutinesActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    private lateinit var job: Job
    private lateinit var binding: ActCoroutinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        job = Job()
        binding.btnClick.setOnClickListener {
            click()
//            tv_show_content.text = "我是click事件中给的值!!!"

        }


    }

    override fun onStop() {
        super.onStop()
        job.cancel()
    }

    private fun click(): Unit {
        uiScope.launch {
            val myJob = uiScope.launch {
                delay(10000L)
                binding.tvShowContent.text = "我是协程中给的值!!!!"
            }
            myJob.join()  //Suspend function 'join' should be called only from a coroutine or another suspend function
            binding.tvShowContent.text = "我是click事件中给的值!!!"
        }

        //挂起函数“join”只能从协程或其他挂起函数调用
//        myJob.join()  //Suspend function 'join' should be called only from a coroutine or another suspend function
//        tv_show_content.text = "我是click事件中给的值!!!"

//        GlobalScope.launch(uiDispatcher + job) {
//            Log.e(TAG, "在UI线程打印一下日志!!!!")
//
//        }

    }
}