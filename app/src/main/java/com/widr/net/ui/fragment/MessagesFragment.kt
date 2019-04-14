package com.widr.net.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.widr.net.ErrorModel
import com.widr.net.R
import com.widr.net.data_flow.network.api_models.MessagesAnswer
import com.widr.net.ui.adapters.MessagesAdapter
import com.widr.net.ui.base.BaseFragment
import com.widr.net.ui.vm.MessagesVM
import com.widr.net.utils.onClick
import com.widr.net.utils.showSnack
import kotlinx.android.synthetic.main.messages_fragment.*
import timber.log.Timber


class MessagesFragment : BaseFragment() {

    override fun getName(): String = "Messages fragment"

    private lateinit var viewModel: MessagesVM
    private var searchAnimationDone: Boolean = false
    private val messages: ArrayList<MessagesAnswer> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(MessagesVM::class.java)
        viewModel.getListOfMessages().observe(this, Observer { servers -> servers?.let { showList(servers) } })
        return inflater.inflate(R.layout.messages_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messagesList.setHasFixedSize(true)
        messagesList.adapter = MessagesAdapter(messages, { onClickLog(it) })
        messagesSearch.onClick { if (isClickAllowed()) openSearch() }
    }

    private fun openSearch() {
        val searchAnimation = messagesSearch.drawable
        if (searchAnimation is AnimatedVectorDrawable) {
            searchAnimationDone = if (searchAnimationDone) {
                searchAnimation.reset()
                false
            } else {
                searchAnimation.start()
                true
            }
        }
    }

    private fun showList(sites: List<MessagesAnswer>) {
        Timber.d("Get message list in UI ${sites.size}")
        messages.clear()
        messages.addAll(sites)
        messagesList.adapter.notifyDataSetChanged()
    }

    private fun onClickLog(server: MessagesAnswer) {
        Timber.d("On click was fired ")
        activity?.showSnack(ErrorModel(string = "ServerEntity ${server.name} was chosen"))
    }
}