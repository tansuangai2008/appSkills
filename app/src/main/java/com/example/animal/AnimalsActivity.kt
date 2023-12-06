package com.example.animal

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActAnimalsBinding


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

    private lateinit var binding: ActAnimalsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActAnimalsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvAlpha.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                showAlphaAnimals()
            }

        })
        binding.tvRotation.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                showRotationAnimals()
            }

        })

        binding.tvTranslationX.setOnClickListener {
            showTranslationXAnimals()
        }

        binding.tvScaleY.setOnClickListener {
            showScaleYAnimals()
        }
        binding.tvCombineAnimal.setOnClickListener {
            showCombineAnimals()
        }
    }


    /**
     * 展示基本的属性动画
     */
    private fun showAlphaAnimals() {
        val animator = ObjectAnimator.ofFloat(binding.tvAnimal, "alpha", 1f, 0f, 1f)
        animator.duration = 5000
        animator.start()


    }

    private fun showRotationAnimals() {
        val animator = ObjectAnimator.ofFloat(binding.tvAnimal, "rotation", 0f, 360f)
        animator.duration = 5000
        animator.start()
    }

    private fun showTranslationXAnimals() {
        val curTranslationX: Float = binding.tvAnimal.translationX
        val animator = ObjectAnimator.ofFloat(binding.tvAnimal, "translationX", curTranslationX, -500f, curTranslationX)
        animator.duration = 5000
        animator.start()

    }


    private fun showScaleYAnimals() {
        val animator = ObjectAnimator.ofFloat(binding.tvAnimal, "scaleY", 1f, 3f, 1f)
        animator.duration = 5000
        animator.start()
    }


    /**
     * 组合动画
     */
    private fun showCombineAnimals() {
        val moveIn = ObjectAnimator.ofFloat(binding.tvAnimal, "translationX", -500f, 0f)
        val rotate = ObjectAnimator.ofFloat(binding.tvAnimal, "rotation", 0f, 360f)
        val fadeInOut = ObjectAnimator.ofFloat(binding.tvAnimal, "alpha", 1f, 0f, 1f)
        val animSet = AnimatorSet()
        animSet.play(rotate).with(fadeInOut).after(moveIn)
        animSet.duration = 5000
        animSet.start()
    }

    /**
     *
     */


}