package com.widr.net.data_flow.interactions

import com.widr.net.data_flow.network.api_models.TokenAnswer
import io.reactivex.Observable


interface ILoginInteractor {

    fun getTokenFormServer(userName: String, password: String): Observable<TokenAnswer>
}