package com.widr.net.ui.vm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.widr.net.AndroidApplication
import com.widr.net.data_flow.interactions.IChatInteractor
import com.widr.net.data_flow.network.api_models.MessagesAnswer
import com.widr.net.ui.base.BaseViewModel
import javax.inject.Inject

class MessagesVM : BaseViewModel() {

    @Inject lateinit var chatInteractor: IChatInteractor

    init {
        AndroidApplication.component.inject(this)
    }

    fun getListOfMessages(): LiveData<List<MessagesAnswer>> {
        return MutableLiveData<List<MessagesAnswer>>().apply {
            value = getMessagesList()
        }
    }

    private fun getMessagesList(): List<MessagesAnswer> = chatInteractor.getListOfRooms()

    fun setChatRoom(message: MessagesAnswer) {
        chatInteractor.currentRoom = message
    }
}