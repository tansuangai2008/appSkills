package com.example.storage

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.act_album.*
import kotlin.concurrent.thread

/**
 *  author : ly
 *  date : 2020/12/9 14:02
 *  description : android 10 上测试、读取相册图片
 */
class AlbumBrowerActivity : BaseActivity() {

    val imageList = ArrayList<Uri>()

    companion object {
        private val TAG = AlbumBrowerActivity::class.java.simpleName
        fun startAct(context: Context) {
            val intent = Intent()
            intent.setClass(context, AlbumBrowerActivity::class.java)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_album)
        rv_albums.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                rv_albums.viewTreeObserver.removeOnPreDrawListener(this)
                val columns = 3
                val imageSize = rv_albums.width / columns
                val adapter = AlbumAdapter(this@AlbumBrowerActivity, imageList, imageSize)
                rv_albums.layoutManager = GridLayoutManager(this@AlbumBrowerActivity, columns)
                rv_albums.adapter = adapter
                loadImages(adapter)
                return false
            }
        })
    }

    private fun loadImages(adapter: AlbumAdapter) {
        thread {
            val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, "${MediaStore.MediaColumns.DATE_ADDED} desc")
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID))
                    val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                    imageList.add(uri)
                }
                cursor.close()
            }
            runOnUiThread {
                adapter.notifyDataSetChanged()
            }
        }
    }

}
