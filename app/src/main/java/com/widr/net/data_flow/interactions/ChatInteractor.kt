package com.widr.net.data_flow.interactions

import com.widr.net.data_flow.database.LocalDatabase
import com.widr.net.data_flow.network.api_models.MessagesAnswer

/**
 * Created by a.belichenko on 15.04.2019.
 * mail: a.belichenko@gmail.com
 */
class ChatInteractor(private val db: LocalDatabase): IChatInteractor {

    override var currentMessage: MessagesAnswer
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
}