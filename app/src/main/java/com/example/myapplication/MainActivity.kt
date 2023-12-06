package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import com.example.animal.AnimalsActivity
import com.example.coordinator.CoordinatorActivity
import com.example.coroutines.CoroutinesActivity
import com.example.databinding.PersonActivity
import com.example.dispatch.TouchActivity
import com.example.fragment.UseFragmentActivity
import com.example.mvp.UserInfoActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myruler.MyRulerViewActivity
import com.example.nightmode.NightModeActivity
import com.example.photoalbum.common.media.imagepicker.Constants
import com.example.photoalbum.common.media.imagepicker.ImagePickerLauncher
import com.example.photoalbum.common.media.imagepicker.ui.ImageTakeActivity
import com.example.photoalbum.common.media.model.GLImage
import com.example.recyclehelper.ui.RecycleViewHelperActivity
import com.example.room.ui.RoomUseActivity
import com.example.signatureview.SlideViewActivity
import com.example.storage.AlbumActivity
import com.example.view.ui.MyCustomViewActivity
import com.tencent.mmkv.MMKV


/**
 * demo 入口主页
 */
public class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val kv = MMKV.defaultMMKV()
        kv.encode("bool", true)
        Log.e(TAG, "bool: " + kv.decodeBool("bool"))
        binding.tvUseRoom.test()
        binding.llUseRoom.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity, RoomUseActivity::class.java)
            startActivity(intent)
        })

        binding.llDiyView.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity, MyCustomViewActivity::class.java)
            startActivity(intent)
        })
        binding.llSignatureView.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity, SlideViewActivity::class.java)
            startActivity(intent)
        })
        binding.llRecycleHelper.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity, RecycleViewHelperActivity::class.java)
            startActivity(intent)
        })
        binding.llNightMode.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity, NightModeActivity::class.java)
            startActivity(intent)
        })
        binding.llPhotoAlbum.setOnClickListener(View.OnClickListener { ImagePickerLauncher.pickImage(this@MainActivity, PICK_AVATAR_REQUEST, R.string.set_head_image) })
        var viewTreeObserver = binding.llPhotoAlbum.viewTreeObserver
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                //判断ViewTreeObserver 是否live ,如果存在就移除
                if (viewTreeObserver.isAlive) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                    //获取宽、高
                    var viewWidth = binding.llPhotoAlbum.measuredWidth
                    var viewHeight = binding.llPhotoAlbum.measuredHeight
                    Log.e(TAG, "viewWidth = $viewWidth viewHeight = $viewHeight")
                }
            }
        })

        binding.llLimitLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                ConstraintActivity.startAct(this@MainActivity)
            }

        })
        binding.llTouchLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                TouchActivity.startAct(this@MainActivity)
            }

        })
        binding.llStorageLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
//                AlbumBrowerActivity.startAct(this@MainActivity)
                AlbumActivity.startAct(this@MainActivity)
            }

        })
        binding.llCoroutinesLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                CoroutinesActivity.startAct(this@MainActivity)
            }

        })

        binding.llFragmentLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                UseFragmentActivity.startAct(this@MainActivity)
            }

        })
        binding.llAnimalLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                AnimalsActivity.startAct(this@MainActivity)
            }

        })

        binding.llMvpLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                UserInfoActivity.startAct(this@MainActivity)
            }

        })
        binding.llDatabindingLayout.setOnClickListener {
            PersonActivity.startAct(this@MainActivity)
        }
        binding.llCoordinatorLayout.setOnClickListener {
            CoordinatorActivity.startAct(this@MainActivity)
        }

        binding.llMatrixLayout.setOnClickListener {
            getTagStr("LaBuLa!!")
//            MatrixActivity.startAct(this@MainActivity)
            MyRulerViewActivity.startAct(this@MainActivity)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_AVATAR_REQUEST) {
            Log.e(TAG, "获取到图片路径====")
            onPicked(data)
        } else if (resultCode == ImageTakeActivity.RESULT_OK_IMAGE && requestCode == PICK_AVATAR_REQUEST) {
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

    public fun getTagStr(tempStr: String): String {
        return "Str"
    }

}