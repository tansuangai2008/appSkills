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
import com.example.myapplication.databinding.FragmentCircleViewBinding
import com.example.view.animal.MorphingAnimator
import com.example.view.drawable.StrokeGradientDrawable
import com.example.view.listener.OnAnimationEndListener

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
    private lateinit var fragmentCircleViewBinding: FragmentCircleViewBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentCircleViewBinding = FragmentCircleViewBinding.inflate(inflater, container, false)
        return fragmentCircleViewBinding.root
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
        fragmentCircleViewBinding.bt.setBackgroundDrawable(gradientDrawable)
        fragmentCircleViewBinding.bt.text = "动画结束"
        fragmentCircleViewBinding.bt.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                fragmentCircleViewBinding.cvView.start()
                if (!flag) {
                    flag = true
                    var animator = MorphingAnimator(fragmentCircleViewBinding.bt, drawable)
                    animator.setDuration(2000)
                    animator.setFromColor(Color.RED)
                    animator.setToColor(Color.BLUE)
                    fragmentCircleViewBinding.bt.text = "开始"
                    animator.setListener {
                        Log.i(TAG, "onAnimationEnd: 动画结束！！")
                        isStart = false
                    }
                    animator.setFromWidth(fragmentCircleViewBinding.bt.width)
                    animator.setToWidth(fragmentCircleViewBinding.bt.height)
                    animator.setFromCornerRadius(5 * density)
                    animator.setToCornerRadius(40 * density)
                    animator.start()
                } else {
                    //增大
                    flag = false
                    val animator = MorphingAnimator(fragmentCircleViewBinding.bt, drawable)
                    animator.setDuration(2000)
                    animator.setFromColor(Color.RED)
                    animator.setToColor(Color.BLUE)
                    animator.setListener(object : OnAnimationEndListener {
                        override fun onAnimationEnd() {
                            Log.i("TAG", "onAnimationEnd: 动画结束！！")
                        }
                    })
                    fragmentCircleViewBinding.bt.text = "动画结束"
                    animator.setFromCornerRadius(40 * density)
                    animator.setToCornerRadius(5 * density)
                    animator.setFromWidth(fragmentCircleViewBinding.bt.height)
                    animator.setToWidth(fragmentCircleViewBinding.bt.width)
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