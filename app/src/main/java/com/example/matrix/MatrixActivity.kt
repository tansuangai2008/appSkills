package com.example.matrix

import android.content.Context
import android.content.Intent
import android.graphics.Matrix
import android.os.Bundle
import android.util.Log
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.act_matrix.*

/**
 *  author : ly
 *  date : 2021/1/5 10:57
 *  description : matrix 试炼
 */
public class MatrixActivity : BaseActivity() {

    companion object {
        val TAG: String = MatrixActivity::class.java.simpleName
        fun startAct(context: Context) {
            val intent = Intent()
            intent.setClass(context, MatrixActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_matrix)
        var matrix = Matrix()
//        matrix.setRotate(45F,iv_matrix_fruit.width/2f, iv_matrix_fruit.height/2f)
        iv_matrix_fruit.post {
            Log.e(TAG, "iv_matrix_fruit.width = "+iv_matrix_fruit.width)
//            matrix.setScale(0.5f,0.5f, iv_matrix_fruit.width/2f, iv_matrix_fruit.height/2f)
            matrix.setRotate(45F)
            Log.e(TAG, "matrix ="+ matrix.toString())
            iv_matrix_fruit.imageMatrix = matrix
        }

    }
}