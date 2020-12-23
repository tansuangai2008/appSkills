package com.example.animal

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.act_animals.*


/**
 *  author : ly
 *  date : 2020/12/22 19:00
 *  description :
 */
class AnimalsActivity : BaseActivity() {

    companion object {
        val TAG: String = AnimalsActivity::class.java.simpleName
        fun startAct(context: Context) {
            val intent = Intent()
            intent.setClass(context, AnimalsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_animals)
        tv_alpha.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                showAlphaAnimals()
            }

        })
        tv_rotation.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                showRotationAnimals()
            }

        })

        tv_translationX.setOnClickListener {
            showTranslationXAnimals()
        }

        tv_scaleY.setOnClickListener {
            showScaleYAnimals()
        }
        tv_combine_animal.setOnClickListener {
            showCombineAnimals()
        }
    }


    /**
     * 展示基本的属性动画
     */
    private fun showAlphaAnimals() {
        val animator = ObjectAnimator.ofFloat(tv_animal, "alpha", 1f, 0f, 1f)
        animator.duration = 5000
        animator.start()


    }

    private fun showRotationAnimals() {
        val animator = ObjectAnimator.ofFloat(tv_animal, "rotation", 0f, 360f)
        animator.duration = 5000
        animator.start()
    }

    private fun showTranslationXAnimals() {
        val curTranslationX: Float = tv_animal.translationX
        val animator = ObjectAnimator.ofFloat(tv_animal, "translationX", curTranslationX, -500f, curTranslationX)
        animator.duration = 5000
        animator.start()
        Integer.parseInt

    }


    private fun showScaleYAnimals() {
        val animator = ObjectAnimator.ofFloat(tv_animal, "scaleY", 1f, 3f, 1f)
        animator.duration = 5000
        animator.start()
    }


    /**
     * 组合动画
     */
    private fun showCombineAnimals() {
        val moveIn = ObjectAnimator.ofFloat(tv_animal, "translationX", -500f, 0f)
        val rotate = ObjectAnimator.ofFloat(tv_animal, "rotation", 0f, 360f)
        val fadeInOut = ObjectAnimator.ofFloat(tv_animal, "alpha", 1f, 0f, 1f)
        val animSet = AnimatorSet()
        animSet.play(rotate).with(fadeInOut).after(moveIn)
        animSet.duration = 5000
        animSet.start()
    }

    /**
     *
     */


}