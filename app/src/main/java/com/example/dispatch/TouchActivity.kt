package com.example.dispatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.datastore.userInfoStore
import com.example.myapplication.BaseActivity
import com.example.myapplication.ConstraintActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActTouchInfoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.net.URLDecoder

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

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActTouchInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.llAtouchviewgroup.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.e(TAG, "想要干嘛呢!!!!")
            }

        })

        uiScope.launch {

            //将内容写入 Proto DataStore
            userInfoStore.updateData {
                it.toBuilder()
                    .setName("今阳")
                    .setAge(18)
                    .setIsMarried(true)
                    .build()
            }
            userInfoStore.data.collect{
                if(it.name is String){
                    Log.e(TAG, "userInfoStore.data!!!! ==="+ URLDecoder.decode(it.name, "UTF-8"))
                }else {
                    Log.e(TAG, "userInfoStore.dataBBBB ===$it")

                }
            }
        }

    }
}