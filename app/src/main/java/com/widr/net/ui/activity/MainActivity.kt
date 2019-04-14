package com.widr.net.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.widr.net.R
import com.widr.net.ui.base.BaseActivity
import com.widr.net.ui.base.BaseFragment
import com.widr.net.ui.views.BottomIconView
import com.widr.net.ui.vm.MainActivityVM
import com.widr.net.utils.changeStatusBarColor
import com.widr.net.utils.onClick
import com.widr.net.utils.showNewFragment
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainActivityVM

    private var lastPage: BottomIconView? = null
        set(value) {
            field?.let { if (it.id != value?.id) it.checked = false }
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeStatusBarColor(R.color.colorViolet)
        setContentView(R.layout.main_activity)
        viewModel = ViewModelProviders.of(this).get(MainActivityVM::class.java)
        viewModel.getNextScreen().observe(this, Observer { openNextScreen(it) })
        initBottomNavigation()
    }

    private fun openNextScreen(fragment: Class<out BaseFragment>?) {
        fragment?.let {
            showNewFragment(it, false)
        }
    }

    private fun initBottomNavigation() {
        lastPage = feedIcon
        feedIcon.checked =true
        feedIcon.onClick { lastPage = feedIcon }
        msgIcon.onClick { lastPage = msgIcon}
        newPostIcon.onClick { }
        notifIcon.onClick { lastPage = notifIcon }
        profileIcon.onClick { lastPage = profileIcon }
    }
}