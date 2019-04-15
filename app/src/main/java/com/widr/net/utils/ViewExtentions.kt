package com.widr.net.utils

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.View
import android.widget.EditText
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

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}



