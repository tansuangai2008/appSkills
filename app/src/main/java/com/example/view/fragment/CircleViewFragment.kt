package com.example.view.fragment

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.example.fragment.BaseFragment
import com.example.myapplication.R
import com.example.view.animal.MorphingAnimator
import com.example.view.drawable.StrokeGradientDrawable
import com.example.view.listener.OnAnimationEndListener
import kotlinx.android.synthetic.main.fragment_circle_view.view.*

/**
 *  author : ly
 *  date : 2020/12/24 14:02
 *  description :
 */
public class CircleViewFragment : BaseFragment() {

    private var drawable: StrokeGradientDrawable? = null
    private var gradientDrawable: GradientDrawable? = null
    private var linearOutSlowInInterpolator: LinearOutSlowInInterpolator? = null
    private var density = 0f
    private var flag: Boolean = false
    private var isStart: Boolean = false
    private lateinit var mView: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_circle_view, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        linearOutSlowInInterpolator = LinearOutSlowInInterpolator()
        drawable = createDrawable(Color.RED)
        gradientDrawable = drawable?.gradientDrawable
        density = resources.displayMetrics.density
        mView.bt.setBackgroundDrawable(gradientDrawable)
        mView.bt.text = "动画结束"
        mView.bt.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mView.cv_view.start()
                if (!flag) {
                    flag = true
                    var animator = MorphingAnimator(mView.bt, drawable)
                    animator.setDuration(2000)
                    animator.setFromColor(Color.RED)
                    animator.setToColor(Color.BLUE)
                    mView.bt.text = "开始"
                    animator.setListener {
                        Log.i(TAG, "onAnimationEnd: 动画结束！！")
                        isStart = false
                    }
                    animator.setFromWidth(mView.bt.width)
                    animator.setToWidth(mView.bt.height)
                    animator.setFromCornerRadius(5 * density)
                    animator.setToCornerRadius(40 * density)
                    animator.start()
                } else {
                    //增大
                    flag = false
                    val animator = MorphingAnimator(mView.bt, drawable)
                    animator.setDuration(2000)
                    animator.setFromColor(Color.RED)
                    animator.setToColor(Color.BLUE)
                    animator.setListener(object : OnAnimationEndListener {
                        override fun onAnimationEnd() {
                            Log.i("TAG", "onAnimationEnd: 动画结束！！")
                        }
                    })
                    mView.bt.text = "动画结束"
                    animator.setFromCornerRadius(40 * density)
                    animator.setToCornerRadius(5 * density)
                    animator.setFromWidth(mView.bt.height)
                    animator.setToWidth(mView.bt.width)
                    animator.start()
                }
            }
        })
    }

    private fun createDrawable(color: Int): StrokeGradientDrawable {
        var drawable = this.resources.getDrawable(R.drawable.shape_button).mutate() as GradientDrawable
        drawable.setColor(color)
        drawable.cornerRadius = 54f
        val strokeGradientDrawable = StrokeGradientDrawable(drawable)
        strokeGradientDrawable.strokeColor = color
        strokeGradientDrawable.strokeWidth = 15
        return strokeGradientDrawable
    }


}