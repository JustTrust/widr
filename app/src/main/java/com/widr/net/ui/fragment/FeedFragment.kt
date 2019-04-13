package com.widr.net.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.widr.net.ErrorModel
import com.widr.net.R
import com.widr.net.data_flow.database.entities.ServerEntity
import com.widr.net.ui.adapters.HeaderPageAdapter
import com.widr.net.ui.adapters.ServerAdapter
import com.widr.net.ui.base.BaseFragment
import com.widr.net.ui.vm.ServerListVM
import com.widr.net.utils.DividerItemDecoration
import com.widr.net.utils.showSnack
import kotlinx.android.synthetic.main.fragment_feed.*
import timber.log.Timber


class FeedFragment : BaseFragment() {

    override fun getName(): String = "Server list fragment"

    private lateinit var viewModel: ServerListVM
    private val listSites: ArrayList<ServerEntity> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(ServerListVM::class.java)
        viewModel.getListOfServers().observe(this, Observer { servers -> servers?.let { showList(servers) } })
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { feedList.addItemDecoration(DividerItemDecoration(it)) }
        feedList.adapter = ServerAdapter(listSites, { onClickLog(it) })
        val tabList = ArrayList<String>().apply {
            add("Toutes les annonces")
            add("Explorer les offres")
            add("Trouver un profits")
            add("Trouver un profiles")
        }
        tabs.isTabSpaceEqual = false
        tabs.isTextAllCaps = false
        tabs.tabPadding = 4f
        tabs.setViewPager(ViewPager(activity!!).apply { adapter = HeaderPageAdapter(activity!!.supportFragmentManager) }, tabList.toTypedArray())
    }

    private fun showList(sites: List<ServerEntity>) {
        Timber.d("Get site list in UI ${sites.size}")
        listSites.clear()
        listSites.addAll(sites)
        feedList.adapter.notifyDataSetChanged()
    }

    private fun onClickLog(server: ServerEntity) {
        Timber.d("On click was fired ")
        activity?.showSnack(ErrorModel(string = "ServerEntity ${server.name} was chosen"))
    }
}