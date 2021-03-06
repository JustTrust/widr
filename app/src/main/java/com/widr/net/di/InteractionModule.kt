package com.widr.net.di

import com.widr.net.data_flow.IUserStorage
import com.widr.net.data_flow.database.LocalDatabase
import com.widr.net.data_flow.interactions.*
import com.widr.net.data_flow.network.Api
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractionModule {

    @Provides
    @Singleton
    fun provideTokenInteractor(pref: IUserStorage): ITokenInteractor = TokenInteractor(pref)

    @Provides
    @Singleton
    fun provideLoginInteractor(api: Api): ILoginInteractor = LoginInteractor(api)

    @Provides
    @Singleton
    fun provideSitesInteractor(api: Api, db: LocalDatabase): IServerInteractor = ServerInteractor(api, db)

    @Provides
    @Singleton
    fun provideChatInteractor(db: LocalDatabase): IChatInteractor = ChatInteractor(db)
}