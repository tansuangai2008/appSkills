package com.example.matrix

import android.R.attr
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.example.myapplication.R


/**
 *  author : ly
 *  date : 2021/1/5 15:03
 *  description : Matrix 试炼
 */
public class MatrixView : View {

    private var bitmap: Bitmap? = null


    constructor(context: Context?) : super(context) {
        this.init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        this.init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.init()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        bitmap?.let {
            var afterBitmap = Bitmap.createBitmap(
                    (it.getWidth() * attr.x) as Int,
                    (it.getHeight() * attr.y) as Int, it.getConfig())
//            val canvas = Canvas(afterBitmap)
            // 初始化Matrix对象
            val matrix = Matrix()
            // 依据传入的參数设置缩放比例
            matrix.setScale(attr.x.toFloat(), attr.y.toFloat())
            // 依据缩放比例。把图片draw到Canvas上
//            canvas?.drawBitmap(it, matrix, canvas)
//            this.setImageBitmap(afterBitmap)
        }




    }


    private fun init(){
        bitmap = BitmapFactory.decodeResource(resources, R.mipmap.fruit1)
    }

}