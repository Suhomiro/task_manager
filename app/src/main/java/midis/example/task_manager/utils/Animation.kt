package midis.example.task_manager.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View

fun fadeInAnimation(viewToFadeIn: View) {
    val fadeIn = ObjectAnimator.ofFloat(viewToFadeIn, "alpha", 0f, 1f)
    fadeIn.duration = 1000
    fadeIn.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            viewToFadeIn.visibility = View.VISIBLE
            viewToFadeIn.alpha = 0f
        }
    })
    fadeIn.start()
}

fun fadeOutAnimation(viewToFadeOut: View) {
    val fadeOut = ObjectAnimator.ofFloat(viewToFadeOut, "alpha", 1f, 0f)
    fadeOut.duration = 1000
    fadeOut.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            viewToFadeOut.visibility = View.GONE
        }
    })
    fadeOut.start()
}