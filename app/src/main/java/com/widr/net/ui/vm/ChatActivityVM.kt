package com.widr.net.ui.vm

import com.widr.net.AndroidApplication
import com.widr.net.data_flow.interactions.IChatInteractor
import com.widr.net.ui.base.BaseViewModel
import javax.inject.Inject

class ChatActivityVM : BaseViewModel() {

    @Inject lateinit var chatInteractor: IChatInteractor

    init {
        AndroidApplication.component.inject(this)
    }

    fun getChatRom() = chatInteractor.currentRoom

    fun getChatList() = chatInteractor.chatList

    fun sendNewMessage(newMsg: String) {
        chatInteractor.addNewMsg(newMsg)
    }
}