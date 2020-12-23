package com.example.mvp

import android.util.Log
import androidx.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

public class SexTest {
    @get:Sex
    val sex = 0

    @IntDef(*[MAN, WOMEN]) //限定为MAN,WOMEN
    @Retention(RetentionPolicy.SOURCE) //表示注解所存活的时间,在运行时,而不会存在. class 文件.
    annotation class Sex { //接口，定义新的注解类型
    }

    companion object {
        const val MAN = 101
        const val WOMEN = 102
        public fun setSex(@Sex sex: Int) {
            when (sex) {
                MAN -> {
                    if (1 != 1) {
                        Log.e("SexTest", "1 != 1")
                    }
                    if (2 == 2) {
                        Log.e("SexTest", "2 == 2")
                    }
                    println("000000")
                }
                WOMEN -> {
                }
            }
        }
    }
}