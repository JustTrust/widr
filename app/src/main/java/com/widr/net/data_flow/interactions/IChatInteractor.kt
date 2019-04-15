package com.widr.net.data_flow.interactions

import android.arch.lifecycle.MutableLiveData
import com.widr.net.data_flow.network.api_models.ChatMessageAnswer
import com.widr.net.data_flow.network.api_models.MessagesAnswer

interface IChatInteractor {

    var currentRoom: MessagesAnswer?

    val chatList: MutableLiveData<MutableList<ChatMessageAnswer>>

    fun getListOfRooms(): List<MessagesAnswer>

    fun addNewMsg(newMsg: String, starAnswer: Boolean = true)
}
