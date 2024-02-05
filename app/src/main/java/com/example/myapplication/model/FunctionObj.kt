package com.example.myapplication.model

import java.io.Serializable

/**
 *    author : LIU YANG
 *    date   : 2024/2/5
 *    desc   :
 */
class FunctionObj(
        var label: String,
        var type: String,
) : Serializable {
    companion object {
        const val TYPE_ROOM: String = "01"// 使用Room 数据库
        const val TYPE_CANVAS_USE: String = "02"// canvas 画图
        const val TYPE_ELECT_SIGN: String = "03" //电子签名
        const val TYPE_RECYCLE_DRAG: String = "04"// recycleView 拖动
        const val TYPE_APP_NIGHT_SETTING = "05"//app 的黑夜模式
        const val TYPE_PHOTO_ALBUM = "06"//相册
        const val TYPE_CONSTRAINT_LAYOUT = "07" //约束布局
        const val TYPE_VIEW_EVENTS = "08" //事件传递
        const val TYPE_STORAGE = "09" //android 存储
        const val TYPE_COROUTINE = "10"//携程试炼
        const val TYPE_FRAGMENT = "11"// fragment 试炼
        const val TYPE_ANIMAL = "12" //animal 试炼
        const val TYPE_MVP = "13" //mvp 试炼
        const val TYPE_DATA_BINDING = "14" //dataBinding 试炼
        const val TYPE_COORDINATOR = "15" //coordinator  试炼
        const val TYPE_MATRIX = "16" //matrix 试炼
    }
}