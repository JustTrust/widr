package com.widr.net.utils

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.View
import android.widget.TextView


fun View.onClick(body: () -> Unit) {
    setOnClickListener {
        hideKeyboard()
        body()
    }
}

fun View.hideKeyboard() {
    if (context is Activity) {
        val activity = context as Activity
        activity.hideKeyboard()
    }
}

fun TextView.setTextOrGone(string: String?) {
    visibility = if (TextUtils.isEmpty(string)) {
        text = ""
        View.GONE
    } else {
        text = string
        View.VISIBLE
    }
}

fun View.setVisibility(visible: Boolean?) {
    if (visible == true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun Int.dpToPixel(context: Context?): Int =
        if (context == null) this
        else (this * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()



