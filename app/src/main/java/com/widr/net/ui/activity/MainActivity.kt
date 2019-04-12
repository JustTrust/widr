package com.widr.net.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.widr.net.R
import com.widr.net.ui.base.BaseActivity
import com.widr.net.ui.base.BaseFragment
import com.widr.net.ui.vm.MainActivityVM
import com.widr.net.utils.changeStatusBarColor
import com.widr.net.utils.showNewFragment


class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeStatusBarColor(R.color.colorWhite)
        setContentView(R.layout.main_activity)
        viewModel = ViewModelProviders.of(this).get(MainActivityVM::class.java)
        viewModel.getNextScreen().observe(this, Observer { openNextScreen(it) })
    }

    private fun openNextScreen(fragment: Class<out BaseFragment>?) {
        fragment?.let {
            showNewFragment(it, false)
        }
    }

    override fun onStart() {
        super.onStart()
    }
}