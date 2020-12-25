package com.example.myapplication

import android.widget.TextView

/**
 *  author : ly
 *  date : 2020/12/25 10:27
 *  description : 试试kotlin 的扩展函数
 */
inline fun TextView.test() {
    this.apply {
        paint.isFakeBoldText = true
    }
}

//var TextView.test: Boolean
//    get() {
//        return this.paint.isFakeBoldText
//    }
//    set(value) {
//        this.paint.isFakeBoldText = true
//    }