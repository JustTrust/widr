package com.widr.net.ui.vm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.widr.net.AndroidApplication
import com.widr.net.R
import com.widr.net.data_flow.interactions.IServerInteractor
import com.widr.net.data_flow.interactions.ITokenInteractor
import com.widr.net.data_flow.network.api_models.MessagesAnswer
import com.widr.net.ui.base.BaseViewModel
import javax.inject.Inject

class MessagesVM : BaseViewModel() {

    @Inject lateinit var serverInteractor: IServerInteractor
    @Inject lateinit var tokenInteractor: ITokenInteractor

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
        }
    }
}