package com.widr.net.di

import com.widr.net.ui.vm.MainActivityVM
import com.widr.net.ui.vm.ServerListVM
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    fun inject(siteFragmentVM: ServerListVM)

    fun inject(mainActivityVM: MainActivityVM)
}