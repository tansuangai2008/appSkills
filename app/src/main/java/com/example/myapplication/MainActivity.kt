package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.room.ui.RoomUseActivity
import com.example.view.ui.MyCustomViewActivity
import com.example.signatureview.SlideViewActivity
import com.example.recyclehelper.ui.RecycleViewHelperActivity
import com.example.nightmode.NightModeActivity
import com.example.photoalbum.common.media.imagepicker.ImagePickerLauncher
import android.util.Log
import android.view.View
import com.example.photoalbum.common.media.imagepicker.Constants
import com.example.photoalbum.common.media.model.GLImage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

/**
 * demo 入口主页
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ll_use_room.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity, RoomUseActivity::class.java)
            startActivity(intent)
        })

        ll_diy_view.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity, MyCustomViewActivity::class.java)
            startActivity(intent)
        })
        ll_signature_view.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity, SlideViewActivity::class.java)
            startActivity(intent)
        })
        ll_recycle_helper.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity, RecycleViewHelperActivity::class.java)
            startActivity(intent)
        })
        ll_night_mode.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity, NightModeActivity::class.java)
            startActivity(intent)
        })
        ll_photo_album.setOnClickListener(View.OnClickListener { ImagePickerLauncher.pickImage(this@MainActivity, PICK_AVATAR_REQUEST, R.string.set_head_image) })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_AVATAR_REQUEST) {
            Log.e(TAG, "获取到图片路径====")
            onPicked(data)
        }
    }

    private fun onPicked(data: Intent?) {
        if (data == null) {
            return
        }
        val images = data.getSerializableExtra(Constants.EXTRA_RESULT_ITEMS) as ArrayList<GLImage>?
        if (images == null || images.isEmpty()) {
            return
        }
        val image = images[0]
        Log.e(TAG, "获取选择图片路径===" + image.path)
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val PICK_AVATAR_REQUEST = 0x0E
    }
}