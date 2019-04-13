package com.widr.net.ui.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
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

    var text: String? = null
        set(value) {
            field = value
            initView()
        }

    private var iconId: Int = 0
        set(value) {
            field = value
            initView()
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
        a.recycle()
    }

    private fun initView() {
        notification.setTextOrGone(text)

        if (iconId != 0) {
            icon.setImageResource(iconId)
        } else {
            icon.setImageDrawable(iconDrawable)
        }
    }

}
