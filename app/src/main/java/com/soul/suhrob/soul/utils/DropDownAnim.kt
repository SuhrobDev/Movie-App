package com.soul.suhrob.soul.utils

import android.animation.ValueAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator


object DropDownAnim {
    private var isCollapsedListener: ((Boolean) -> Unit)? = null
    fun setOnCollapsedListener(f: (Boolean) -> Unit) {
        isCollapsedListener = f
    }

    private var isCollapsed = true
    fun isCollapse(v: View, duration: Int, targetHeight: Int): DropDownAnim {
        if (isCollapsed) {
            isCollapsed = false
            isCollapsedListener?.invoke(false)
            collapse(v, duration, 0)
        } else {
            isCollapsed = true
            isCollapsedListener?.invoke(true)
            expand(v, duration, targetHeight)
        }
        return this
    }

    private fun expand(v: View, duration: Int, targetHeight: Int) {
        val prevHeight: Int = v.height
        v.visibility = View.VISIBLE
        val valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight)
        valueAnimator.addUpdateListener { animation ->
            v.layoutParams.height = animation.animatedValue as Int
            v.requestLayout()
        }
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.duration = duration.toLong()
        valueAnimator.start()
    }

    private fun collapse(v: View, duration: Int, targetHeight: Int) {
        val prevHeight: Int = v.height
        val valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight)
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.addUpdateListener { animation ->
            v.layoutParams.height = animation.animatedValue as Int
            v.requestLayout()
        }
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.duration = duration.toLong()
        valueAnimator.start()
    }
}
//binding.tttttttt.setOnClickListener {
//    DropDownAnim.isCollapse(binding.ffffffffffffff,300, dipToPixels(requireContext(),300f).toInt()).setOnCollapsedListener {
//        toast(requireContext(),"Status: ${it}")
//    }
//}