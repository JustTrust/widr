package com.widr.net.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.widr.net.R
import com.widr.net.ui.base.BaseActivity
import com.widr.net.ui.base.BaseFragment
import com.widr.net.ui.vm.ChatActivityVM
import com.widr.net.utils.showNewFragment


class ChatActivity : BaseActivity() {

    private lateinit var viewModel: ChatActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        val flag = window.decorView.systemUiVisibility
        window.decorView.systemUiVisibility = flag or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        setContentView(R.layout.chat_activity)
        viewModel = ViewModelProviders.of(this).get(ChatActivityVM::class.java)
        viewModel.getNextScreen().observe(this, Observer { openNextScreen(it) })
    }

    private fun openNextScreen(fragment: Class<out BaseFragment>?) {
        fragment?.let { showNewFragment(it, false) }
    }

}