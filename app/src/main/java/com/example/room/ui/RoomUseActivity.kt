package com.example.room.ui

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.fastjson.JSON
import com.example.myapplication.BaseActivity
import com.example.myapplication.Dispatcher
import com.example.myapplication.R
import com.example.myapplication.databinding.ActUseRoomBinding
import com.example.room.dao.UserDataBase
import com.example.room.dao.UserDataBase.Companion.getInstance
import com.example.room.model.UserDTO

/**
 *  author : ly
 *  date : 2020/9/15 14:08
 *  description : Room 使用demo
 */
class RoomUseActivity : BaseActivity(), View.OnClickListener {
    companion object {
        private val TAG: String = RoomUseActivity::class.java.simpleName
    }

    private lateinit var bindView: ActUseRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView = ActUseRoomBinding.inflate(layoutInflater)
        setContentView(bindView.root)
        initView()

    }

    private fun initView() {
        bindView.tvAdd.setOnClickListener(this)
        bindView.tvDelete.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_add -> {
                Log.e(TAG, "add add add")
                Dispatcher.getInstance().enqueue(object : Runnable {
                    override fun run() {
                        UserDataBase.getInstance(this@RoomUseActivity).userDAO().insertUser(UserDTO(1, "小明", "北京朝阳区"))
                        var userDTO = getInstance(this@RoomUseActivity).userDAO().loadAllById(1)
                        var message: Message = mHandler.obtainMessage()
                        message.obj = userDTO
                        message.what = 0x001
                        mHandler.sendMessage(message)
                    }
                })
            }
            R.id.tv_delete -> {
                Log.e(TAG, "delete delete delete")
                Dispatcher.getInstance().enqueue(object : Runnable {
                    override fun run() {
                        UserDataBase.getInstance(this@RoomUseActivity).userDAO().deleteUser(UserDTO(1, "小明", "北京朝阳区"))
                        var userList = getInstance(this@RoomUseActivity).userDAO().getAll()
                        var message: Message = mHandler.obtainMessage()
                        message.obj = userList
                        message.what = 0x002
                        mHandler.sendMessage(message)
                    }
                })
            }
        }
    }

    internal var mHandler: Handler = MyHandler()

    inner class MyHandler : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0x001 -> {
                    bindView.tvRoomResult.text = "展示结果:" + JSON.toJSONString(msg.obj)
                }
                0x002 ->{
                    bindView.tvRoomResult.text = "展示结果:" + JSON.toJSONString(msg.obj)
                }
            }
        }
    }
}