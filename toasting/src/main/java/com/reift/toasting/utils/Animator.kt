package com.reift.toasting.utils

import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.Interpolator

internal object Animator {
    fun View.jumpAnimation(){
        val interpolator: Interpolator = BounceInterpolator()

        animate().scaleX(1.2f).scaleY(1.2f).setDuration(100).setInterpolator(interpolator).withEndAction{
            animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).setInterpolator(interpolator).withEndAction {
                animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).setInterpolator(interpolator).withEndAction {
                    animate().scaleX(1f).scaleY(1f).setInterpolator(interpolator).duration = 200
                }
            }
        }
    }
}