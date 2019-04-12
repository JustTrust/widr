package com.widr.net.data_flow.interactions

import com.widr.net.data_flow.network.Api
import com.widr.net.data_flow.network.api_models.TokenAnswer
import io.reactivex.Observable


class LoginInteractor(private val api: Api) : ILoginInteractor {

    override fun getTokenFormServer(userName: String, password: String): Observable<TokenAnswer> {
        return api.getToken(userName, password)
    }
}