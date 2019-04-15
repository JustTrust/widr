package com.widr.net.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.squareup.picasso.Picasso
import com.widr.net.R
import com.widr.net.data_flow.network.api_models.ChatMessageAnswer
import com.widr.net.ui.adapters.ChatAdapter
import com.widr.net.ui.base.BaseActivity
import com.widr.net.ui.vm.ChatActivityVM
import com.widr.net.utils.CircleTransform
import com.widr.net.utils.afterTextChanged
import com.widr.net.utils.onClick
import com.widr.net.utils.setVisibility
import kotlinx.android.synthetic.main.chat_activity.*
import android.content.Intent



class ChatActivity : BaseActivity() {

    private lateinit var viewModel: ChatActivityVM
    private var msgIsEmpty: Boolean = true
    private val msgList = mutableListOf<ChatMessageAnswer>()
    private val OPEN_DOCUMENT_CODE = 34

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        val flag = window.decorView.systemUiVisibility
        window.decorView.systemUiVisibility = flag or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        setContentView(R.layout.chat_activity)
        viewModel = ViewModelProviders.of(this).get(ChatActivityVM::class.java)
        viewModel.getChatList().observe(this, Observer { showList(it) })
        initUI()
    }

    private fun initUI() {
        chatReturn.onClick { finish() }
        chatChevron.onClick { finish() }
        chatList.adapter = ChatAdapter(msgList, { messageSelected(it) })
        viewModel.getChatRom()?.let {
            Picasso.get().load(it.icon).transform(CircleTransform()).into(chatIcon)
            chatName.text = it.name
        }
        chatPhone.inAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        chatPhone.outAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right)

        chatMsg.afterTextChanged { msgWasChanged(it) }
        chatPhone.onClick { sendNewMessage() }
        chatPicture.onClick {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, OPEN_DOCUMENT_CODE)
        }
    }

    private fun sendNewMessage() {
        if (chatMsg.text.isNotBlank()) {
            viewModel.sendNewMessage(chatMsg.text.toString())
            chatMsg.text = null
        }
    }

    private fun msgWasChanged(msg: String) {
        if (msgIsEmpty.and(msg.isEmpty().not())) {
            chatPhone.showNext()
            chatCamera.setVisibility(false)
            chatPerson.setVisibility(false)
            chatPicture.setVisibility(false)
        } else if (msg.isEmpty().and(msgIsEmpty.not())) {
            chatPhone.showPrevious()
            chatCamera.setVisibility(true)
            chatPerson.setVisibility(true)
            chatPicture.setVisibility(true)
        }
        msgIsEmpty = msg.isEmpty()
    }

    private fun messageSelected(messageAnswer: ChatMessageAnswer) {
        //not implemented yet
    }

    private fun showList(list: List<ChatMessageAnswer>?) {
        msgList.clear()
        list?.let { msgList.addAll(list) }
        chatList.adapter.notifyDataSetChanged()
        chatList.scrollToPosition(msgList.size - 1)
    }
}