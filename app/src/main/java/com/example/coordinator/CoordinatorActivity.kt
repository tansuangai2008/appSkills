package com.example.coordinator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActCoordinatorBinding
import com.google.android.material.appbar.AppBarLayout
import com.jaeger.library.StatusBarUtil

/**
 *  author : ly
 *  date : 2021/1/4 14:48
 *  description : 协同布局
 */
public class CoordinatorActivity : BaseActivity() {

    companion object {
        val TAG: String = CoordinatorActivity::class.java.simpleName
        fun startAct(context: Context) {
            val intent = Intent()
            intent.setClass(context, CoordinatorActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActCoordinatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActCoordinatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                Log.e(TAG, "onOffsetChanged verticalOffset = $verticalOffset" + " =totalScrollRange=" + appBarLayout?.totalScrollRange)
                if (verticalOffset <= -binding.headLayout.height / 2) {
                    binding.collapsingToolbarLayout.title = "看看就好了!"
                } else {
                    binding.collapsingToolbarLayout.title = " "
                }
            }
        })

    }


    /**
     * 设置毛玻璃效果和沉浸状态栏
     */
    private fun loadBlurAndSetStatusBar() {
        StatusBarUtil.setTranslucent(this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA)
    }

}