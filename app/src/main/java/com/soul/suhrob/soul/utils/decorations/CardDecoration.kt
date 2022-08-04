package com.soul.suhrob.soul.utils.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Microstar on 19.08.2021
 */
class CardDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
//            left = if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(view)==parent.size-1) {
//                spaceHeight
//            }else{
//                0
//            }
            left = spaceHeight
            top = 0
            right = spaceHeight
            bottom = 0
        }
    }
}