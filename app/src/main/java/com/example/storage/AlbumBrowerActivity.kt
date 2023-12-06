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
import com.example.myapplication.databinding.ActAlbumBinding
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

    private lateinit var binding: ActAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvAlbums.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                binding.rvAlbums.viewTreeObserver.removeOnPreDrawListener(this)
                val columns = 3
                val imageSize = binding.rvAlbums.width / columns
                val adapter = AlbumAdapter(this@AlbumBrowerActivity, imageList, imageSize)
                binding.rvAlbums.layoutManager = GridLayoutManager(this@AlbumBrowerActivity, columns)
                binding.rvAlbums.adapter = adapter
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
