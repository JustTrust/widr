package com.widr.net.data_flow.network.api_models

/**
 * Created by a.belichenko on 14.04.2019.
 * mail: a.belichenko@gmail.com
 */
data class MessagesAnswer(
        val name: String,
        val lastMsg: String,
        val position: String,
        val icon: Int,
        val city: String,
        val date: String,
        val hot: Boolean
)