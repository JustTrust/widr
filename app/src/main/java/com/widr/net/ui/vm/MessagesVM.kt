package com.widr.net.ui.vm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.widr.net.AndroidApplication
import com.widr.net.R
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

    private fun getMessagesList(): List<MessagesAnswer> {
        return ArrayList<MessagesAnswer>().apply {
            add(MessagesAnswer("Edvide Arnaut",
                    "There are millions of users, and there will be much more",
                    "Graphic Designer - 2D Artist",
                    R.drawable.images,
                    "Havana",
                    "11:24 AM",
                    true))
            for (i in 1..12) {
                add(MessagesAnswer("Lubosec Hnilo".plus(i.toString()),
                        "There are millions of users, and there will be much more",
                        "Déménagement avec camion",
                        if (i % 2 == 1) R.drawable.images2 else R.drawable.images,
                        if (i % 2 == 1)"Algiers" else "Bangalore",
                        "12 Jan",
                        i % 6 == 1))
            }
        }
    }

    fun setChatRoom(message: MessagesAnswer) {
        chatInteractor.currentMessage = message
    }
}