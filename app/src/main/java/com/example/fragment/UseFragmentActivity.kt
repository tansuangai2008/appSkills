package com.example.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
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
    lateinit var mFragPageAdapter: MFragPageAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_use_fragment)
        var fragmentA = FragmentA()
        var fragmentB = FragmentB()
        fragments.add(fragmentA)
        fragments.add(fragmentB)
        mFragPageAdapter = MFragPageAdapter(supportFragmentManager, fragments)
        vp_fragments.adapter = mFragPageAdapter;

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