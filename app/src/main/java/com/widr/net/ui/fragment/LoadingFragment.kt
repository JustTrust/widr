package com.widr.net.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.widr.net.R
import com.widr.net.ui.base.BaseFragment
import com.widr.net.ui.vm.LoadingFragmentVM
import com.widr.net.utils.showNewFragment
import com.widr.net.utils.showSnack

class LoadingFragment : BaseFragment() {

    override fun getName(): String = "Loading fragment"

    private lateinit var viewModel: LoadingFragmentVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(LoadingFragmentVM::class.java)
        viewModel.getError().observe(this, Observer { activity?.showSnack(it) })
        viewModel.getNextScreen().observe(this, Observer { openNextScreen() })
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    private fun openNextScreen() {
        activity?.showNewFragment(ServerListFragment::class.java, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchingList()
    }
}