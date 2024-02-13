package com.example.myapplication

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animal.AnimalsActivity
import com.example.coordinator.CoordinatorActivity
import com.example.coroutines.CoroutinesActivity
import com.example.databinding.PersonActivity
import com.example.dispatch.TouchActivity
import com.example.fragment.UseFragmentActivity
import com.example.mvp.UserInfoActivity
import com.example.myapplication.adapter.FunctionAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.FunctionObj
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
import com.example.view.DividerDrawable
import com.example.view.ui.MyCustomViewActivity
import com.tencent.mmkv.MMKV


/**
 * demo 入口主页
 */
public class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var functionAdapter: FunctionAdapter
    var list = mutableListOf<FunctionObj>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val kv = MMKV.defaultMMKV()
        kv.encode("bool", true)
        Log.e(TAG, "bool: " + kv.decodeBool("bool"))
        binding.tvUseRoom.test()

        initList()
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvFunctions.layoutManager = linearLayoutManager
        val decoration = DividerItemDecoration(binding.rvFunctions.context, linearLayoutManager.orientation)
        decoration.setDrawable(DividerDrawable(ContextCompat.getColor(binding.root.context, R.color.white), resources.getDimensionPixelOffset(R.dimen.margin_24dp)))
        binding.rvFunctions.addItemDecoration(decoration)
        functionAdapter = FunctionAdapter(this, list)
        binding.rvFunctions.adapter = functionAdapter
        functionAdapter.itemOnclickListener = object : FunctionAdapter.ItemOnclickListener {
            override fun onClick(any: Any, position: Int) {
                val obj = any as FunctionObj
                when (obj.type) {
                    FunctionObj.TYPE_ROOM -> {
                        val intent = Intent()
                        intent.setClass(this@MainActivity, RoomUseActivity::class.java)
                        startActivity(intent)
                    }

                    FunctionObj.TYPE_CANVAS_USE -> {
                        val intent = Intent()
                        intent.setClass(this@MainActivity, MyCustomViewActivity::class.java)
                        startActivity(intent)
                    }

                    FunctionObj.TYPE_ELECT_SIGN -> {
                        val intent = Intent()
                        intent.setClass(this@MainActivity, SlideViewActivity::class.java)
                        startActivity(intent)
                    }

                    FunctionObj.TYPE_RECYCLE_DRAG -> {
                        val intent = Intent()
                        intent.setClass(this@MainActivity, RecycleViewHelperActivity::class.java)
                        startActivity(intent)
                    }

                    FunctionObj.TYPE_APP_NIGHT_SETTING -> {
                        val intent = Intent()
                        intent.setClass(this@MainActivity, NightModeActivity::class.java)
                        startActivity(intent)
                    }

                    FunctionObj.TYPE_PHOTO_ALBUM -> {
                        ImagePickerLauncher.pickImage(this@MainActivity, PICK_AVATAR_REQUEST, R.string.set_head_image)
                    }

                    FunctionObj.TYPE_CONSTRAINT_LAYOUT -> {
                        ConstraintActivity.startAct(this@MainActivity)
                    }

                    FunctionObj.TYPE_VIEW_EVENTS -> {
                        TouchActivity.startAct(this@MainActivity)
                    }

                    FunctionObj.TYPE_STORAGE -> {
                        AlbumActivity.startAct(this@MainActivity)
                    }

                    FunctionObj.TYPE_COROUTINE -> {
                        CoroutinesActivity.startAct(this@MainActivity)
                    }

                    FunctionObj.TYPE_FRAGMENT -> {
                        UseFragmentActivity.startAct(this@MainActivity)
                    }

                    FunctionObj.TYPE_ANIMAL -> {
                        AnimalsActivity.startAct(this@MainActivity)
                    }

                    FunctionObj.TYPE_MVP -> {
                        UserInfoActivity.startAct(this@MainActivity)
                    }

                    FunctionObj.TYPE_DATA_BINDING -> {
                        PersonActivity.startAct(this@MainActivity)
                    }

                    FunctionObj.TYPE_COORDINATOR -> {
                        CoordinatorActivity.startAct(this@MainActivity)
                    }

                    FunctionObj.TYPE_MATRIX -> {
                        getTagStr("LaBuLa!!")
                        MyRulerViewActivity.startAct(this@MainActivity)
                    }

                    FunctionObj.TYPE_CONSTRAINT_FLOW -> {

                    }
                }
            }
        }

    }

    private fun initList() {
        var obj1 = FunctionObj("使用Room 数据库", FunctionObj.TYPE_ROOM)
        var obj2 = FunctionObj("canvas 画图", FunctionObj.TYPE_CANVAS_USE)
        var obj3 = FunctionObj("电子签名", FunctionObj.TYPE_ELECT_SIGN)
        var obj4 = FunctionObj(" recycleView 拖动", FunctionObj.TYPE_RECYCLE_DRAG)
        var obj5 = FunctionObj(" app 的黑夜模式", FunctionObj.TYPE_APP_NIGHT_SETTING)
        var obj6 = FunctionObj(" 相册", FunctionObj.TYPE_PHOTO_ALBUM)
        var obj7 = FunctionObj(" 约束布局", FunctionObj.TYPE_CONSTRAINT_LAYOUT)
        var obj8 = FunctionObj(" 事件传递", FunctionObj.TYPE_VIEW_EVENTS)
        var obj9 = FunctionObj(" android 存储", FunctionObj.TYPE_STORAGE)
        var obj10 = FunctionObj("携程试炼", FunctionObj.TYPE_COROUTINE)
        var obj11 = FunctionObj("fragment 试炼", FunctionObj.TYPE_FRAGMENT)
        var obj12 = FunctionObj("animal 试炼", FunctionObj.TYPE_ANIMAL)
        var obj13 = FunctionObj("mvp 试炼", FunctionObj.TYPE_MVP)
        var obj14 = FunctionObj("dataBinding 试炼", FunctionObj.TYPE_DATA_BINDING)
        var obj15 = FunctionObj("coordinator  试炼", FunctionObj.TYPE_COORDINATOR)
        var obj16 = FunctionObj("matrix 试炼", FunctionObj.TYPE_MATRIX)
        var obj17 = FunctionObj("Constraint  Flow", FunctionObj.TYPE_CONSTRAINT_FLOW)
        list.add(obj1)
        list.add(obj2)
        list.add(obj3)
        list.add(obj4)
        list.add(obj5)
        list.add(obj6)
        list.add(obj7)
        list.add(obj8)
        list.add(obj9)
        list.add(obj10)
        list.add(obj11)
        list.add(obj12)
        list.add(obj13)
        list.add(obj14)
        list.add(obj15)
        list.add(obj16)
        list.add(obj17)
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