package com.widr.net

import android.support.annotation.StringRes

data class ErrorModel(
        val throwable: Throwable? = null,
        val string: String? = null,
        @StringRes val stringId: Int? = null)