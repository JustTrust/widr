package com.widr.net.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import com.widr.net.R


class DividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private var divider: Drawable? = null

    private val decorationHeight: Int = context.resources.getDimensionPixelSize(R.dimen.decoration_height)

    init {
        val styledAttributes = context.obtainStyledAttributes(ATTRS)
        divider = styledAttributes.getDrawable(0)
        styledAttributes.recycle()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        divider?.let {
            val childCount = parent.childCount
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)

                val params = child.layoutParams as RecyclerView.LayoutParams

                val top = child.bottom + params.bottomMargin
                val bottom = top + it.intrinsicHeight

                it.setBounds(left, top, right, bottom)
                it.draw(c)
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView?, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent != null) {
            outRect.bottom = decorationHeight
        }
    }

    companion object {
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
}