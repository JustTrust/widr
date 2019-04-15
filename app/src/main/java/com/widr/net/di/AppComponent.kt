package com.widr.net.di

import com.widr.net.ui.vm.ChatActivityVM
import com.widr.net.ui.vm.FeedFragmentVM
import com.widr.net.ui.vm.MainActivityVM
import com.widr.net.ui.vm.MessagesVM
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    fun inject(siteFragmentVM: FeedFragmentVM)

    fun inject(messagesVM: MessagesVM)

    fun inject(mainActivityVM: MainActivityVM)

    fun inject(chatActivityVM: ChatActivityVM)
}