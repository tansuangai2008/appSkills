package com.example.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.act_use_fragment.*


/**
 *  author : ly
 *  date : 2020/12/21 13:29
 *  description : fragment的使用
 */
public class UseFragmentActivity : BaseActivity() {

    companion object {
        val TAG: String = UseFragmentActivity::class.java.simpleName
        fun startAct(context: Context) {
            val intent = Intent()
            intent.setClass(context, UseFragmentActivity::class.java)
            context.startActivity(intent)
        }
    }

    var fragments: MutableList<BaseFragment> = mutableListOf()
    lateinit var mFragPageAdapter: MFragPageAdapter
    private var line_width = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_use_fragment)
        var fragmentA = FragmentA()
        var fragmentB = FragmentB()
        fragments.add(fragmentA)
        fragments.add(fragmentB)
        mFragPageAdapter = MFragPageAdapter(supportFragmentManager, fragments)
        vp_fragments.adapter = mFragPageAdapter;

        // TextView字体大小缩放
        tv_show_fragment_a.animate().scaleX(1.2f).duration = 0
        tv_show_fragment_a.animate().scaleY(1.2f).duration = 0


        // 设置下划线宽度
        // 设置下划线宽度
        line_width = (windowManager.defaultDisplay.width / fragments.size)
        val lp = LinearLayout.LayoutParams(v_divider.layoutParams as ViewGroup.MarginLayoutParams)
        v_divider.post {
            Log.e(TAG, "line_width / 2" + line_width / 2 + " v_divider.measuredWidth / 2 =" + v_divider.measuredWidth / 2)
            lp.leftMargin = line_width / 2 - v_divider.measuredWidth / 2
            v_divider.layoutParams = lp
            v_divider.requestLayout()
        }



        vp_fragments.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                float tagerX = arg0 * line_width + arg2 / fragments.size();
//                ViewPropertyAnimator.animate(view).translationX(tagerX)
//                        .setDuration(0);
                Log.e("setOnPageChangeListener", "positionOffsetPixels=$positionOffsetPixels")
                val tagerX: Float = (position * line_width + positionOffsetPixels / fragments.size).toFloat()
                v_divider.animate().translationX(tagerX).duration = 0

            }

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })


//        tv_show_fragment_a.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                val fm: FragmentManager = supportFragmentManager
//                val transaction: FragmentTransaction = fm.beginTransaction()
//                var fragmentA = FragmentA()
//                transaction.replace(R.id.ll_container, fragmentA, "fragmentA")
//                transaction.commit()
//
//            }
//
//        })
//        tv_show_fragment_b.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                val fm: FragmentManager = supportFragmentManager
//                val transaction: FragmentTransaction = fm.beginTransaction()
//                var fragmentB = FragmentB()
//                transaction.replace(R.id.ll_container, fragmentB, "fragmentB")
//                transaction.commit()
//            }
//
//        })

    }
}