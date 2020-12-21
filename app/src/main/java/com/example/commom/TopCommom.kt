@file: JvmName("FileFormatUtil")

package com.example.commom

import java.math.BigDecimal

/**
 *  author : ly
 *  date : 2020/12/17 13:21
 *  description : 顶层函数、属性的基本使用
 */
//这个顶层函数不属于任何一个类，不需要类容器，不需要static关键字
fun formateFileSize(size: Double): String {
    if (size < 0) {
        return "0 KB"
    }

    val kBSize = size / 1024
    if (kBSize < 1) {
        return "$size B"
    }

    val mBSize = kBSize / 1024
    if (mBSize < 1) {
        return "${BigDecimal(kBSize.toString()).setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString()} KB"
    }

    val mGSize = mBSize / 1024
    if (mGSize < 1) {
        return "${BigDecimal(mBSize.toString()).setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString()} MB"
    }

    val mTSize = mGSize / 1024
    if (mTSize < 1) {
        return "${BigDecimal(mGSize.toString()).setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString()} GB"
    }
    return "${BigDecimal(mTSize.toString()).setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString()} TB"
}