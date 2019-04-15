package com.widr.net.data_flow.interactions

import com.widr.net.data_flow.network.api_models.MessagesAnswer

interface IChatInteractor {
    var currentMessage: MessagesAnswer
}
