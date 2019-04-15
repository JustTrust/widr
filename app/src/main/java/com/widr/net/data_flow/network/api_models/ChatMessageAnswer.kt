package com.widr.net.data_flow.network.api_models

/**
 * Created by a.belichenko on 14.04.2019.
 * mail: a.belichenko@gmail.com
 */
data class ChatMessageAnswer(
        val id: Int,
        val msg: String,
        val position: String,
        val icon: Int,
        val date: String,
        val myMsg: Boolean
) {
    companion object {
        fun getMsg(): ChatMessageAnswer = ChatMessageAnswer(
                0,
                "Ask to show mutual contacts",
                "",
                0,
                "",
                false)
    }
}
