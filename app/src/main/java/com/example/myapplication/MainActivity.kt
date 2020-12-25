package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import com.example.animal.AnimalsActivity
import com.example.coroutines.CoroutinesActivity
import com.example.dispatch.TouchActivity
import com.example.fragment.UseFragmentActivity
import com.example.mvp.UserInfoActivity
import com.example.nightmode.NightModeActivity
import com.example.photoalbum.common.media.imagepicker.Constants
import com.example.photoalbum.common.media.imagepicker.ImagePickerLauncher
import com.example.photoalbum.common.media.model.GLImage
import com.example.recyclehelper.ui.RecycleViewHelperActivity
import com.example.room.ui.RoomUseActivity
import com.example.signatureview.SlideViewActivity
import com.example.storage.AlbumActivity
import com.example.view.ui.MyCustomViewActivity
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


/**
 * demo 入口主页
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val kv = MMKV.defaultMMKV()
        kv.encode("bool", true)
        Log.e(TAG,"bool: " + kv.decodeBool("bool"))

        tv_use_room.test()
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
        var viewTreeObserver = ll_photo_album.viewTreeObserver
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                //判断ViewTreeObserver 是否live ,如果存在就移除
                if (viewTreeObserver.isAlive) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                    //获取宽、高
                    var viewWidth = ll_photo_album.measuredWidth
                    var viewHeight = ll_photo_album.measuredHeight
                    Log.e(TAG, "viewWidth = $viewWidth viewHeight = $viewHeight")
                }
            }
        })

        ll_limit_layout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                ConstraintActivity.startAct(this@MainActivity)
            }

        })
        ll_touch_layout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                TouchActivity.startAct(this@MainActivity)
            }

        })
        ll_storage_layout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
//                AlbumBrowerActivity.startAct(this@MainActivity)
                AlbumActivity.startAct(this@MainActivity)
            }

        })
        ll_coroutines_layout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                CoroutinesActivity.startAct(this@MainActivity)
            }

        })

        ll_fragment_layout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                UseFragmentActivity.startAct(this@MainActivity)
            }

        })
        ll_animal_layout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                AnimalsActivity.startAct(this@MainActivity)
            }

        })

        ll_mvp_layout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                UserInfoActivity.startAct(this@MainActivity)
            }

        })



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