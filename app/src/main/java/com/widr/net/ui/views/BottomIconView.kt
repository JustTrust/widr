package com.widr.net.ui.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import com.widr.net.R
import com.widr.net.utils.setTextOrGone
import kotlinx.android.synthetic.main.bootom_icon_view.view.*

/**
 * Simple button with text and icon
 */
class BottomIconView @JvmOverloads constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defStyle: Int = 0) : FrameLayout(context, attributeSet, defStyle) {

    var iconDrawable: Drawable? = null
    var iconSelected: Drawable? = null

    private val iconAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.shrink_grow)

    var checked: Boolean = false
        set(value) {
            if (value) icon.startAnimation(iconAnimation)
            field = value
            icon.setImageDrawable(if (value) iconSelected else iconDrawable)
        }

    var text: String? = null
        set(value) {
            field = value
            notification.setTextOrGone(text)
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.bootom_icon_view, this)
        initAttributes(attributeSet)
        initView()
    }

    private fun initAttributes(attrs: AttributeSet?) {

        val a = context.obtainStyledAttributes(attrs, R.styleable.BottomIconView)

        text = a.getString(R.styleable.BottomIconView_notificationText)
        if (a.hasValue(R.styleable.BottomIconView_bottomIcon)) {
            iconDrawable = a.getDrawable(R.styleable.BottomIconView_bottomIcon)
        }
        if (a.hasValue(R.styleable.BottomIconView_bottomIconSelected)) {
            iconSelected = a.getDrawable(R.styleable.BottomIconView_bottomIconSelected)
        }
        a.recycle()
    }

    private fun initView() {
        notification.setTextOrGone(text)
        icon.setImageDrawable(iconDrawable)
    }

    override fun performClick(): Boolean {
        if (!checked) checked = !checked
        return super.performClick()
    }
}