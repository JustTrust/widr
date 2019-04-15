package com.widr.net.data_flow.interactions

import android.arch.lifecycle.MutableLiveData
import com.widr.net.R
import com.widr.net.data_flow.database.LocalDatabase
import com.widr.net.data_flow.network.api_models.ChatMessageAnswer
import com.widr.net.data_flow.network.api_models.MessagesAnswer
import java.util.*

/**
 * Created by a.belichenko on 15.04.2019.
 * mail: a.belichenko@gmail.com
 */
class ChatInteractor(private val db: LocalDatabase) : IChatInteractor {

    override var currentRoom: MessagesAnswer? = null
        set(value) {
            field = value
            if (value != null) {
                chatList.value = mutableListOf(*value.msg.toTypedArray())
            }
        }

    override val chatList: MutableLiveData<MutableList<ChatMessageAnswer>> = MutableLiveData()

    override fun addNewMsg(newMsg: String, starAnswer: Boolean) {
        var oldList = chatList.value
        if (oldList == null) oldList = mutableListOf()
        oldList.add(ChatMessageAnswer(oldList.size, newMsg, "", 0, "", starAnswer))
        chatList.postValue(oldList)
        if (starAnswer) {
            sendAnswer(newMsg.reversed())
        }
    }

    private fun sendAnswer(answer: String) {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                addNewMsg(answer, false)
            }
        }, 1200)
    }

    override fun getListOfRooms(): List<MessagesAnswer> {
        return ArrayList<MessagesAnswer>().apply {
            add(MessagesAnswer(
                    0,
                    "Edvide Arnaut",
                    "There are millions of users, and there will be much more",
                    "Graphic Designer - 2D Artist",
                    R.drawable.images,
                    "Havana",
                    "11:24 AM",
                    true,
                    listOf(ChatMessageAnswer.getMsg()))
            )
            for (i in 1..12) {
                add(MessagesAnswer(
                        i,
                        "Lubosec Hnilo".plus(i.toString()),
                        "There are millions of users, and there will be much more",
                        "Déménagement avec camion",
                        if (i % 2 == 1) R.drawable.images2 else R.drawable.images,
                        if (i % 2 == 1) "Algiers" else "Bangalore",
                        "12 Jan",
                        i % 6 == 1,
                        listOf(ChatMessageAnswer.getMsg()))
                )
            }
        }
    }
}