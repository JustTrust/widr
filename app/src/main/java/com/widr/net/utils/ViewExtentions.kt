package com.widr.net.utils

import android.app.Activity
import android.text.TextUtils
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


