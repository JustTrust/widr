package com.widr.net.ui.vm

import android.arch.lifecycle.LiveData
import com.widr.net.AndroidApplication
import com.widr.net.data_flow.interactions.ITokenInteractor
import com.widr.net.ui.base.BaseFragment
import com.widr.net.ui.base.BaseViewModel
import com.widr.net.ui.fragment.FeedFragment
import com.widr.net.utils.SingleLiveEvent
import javax.inject.Inject

class MainActivityVM : BaseViewModel() {

    @Inject lateinit var tokenInteractor: ITokenInteractor

    private val nextScreen: SingleLiveEvent<Class<out BaseFragment>> = SingleLiveEvent()

    init {
        AndroidApplication.component.inject(this)
        nextScreen.postValue(FeedFragment::class.java)
    }

    fun getNextScreen(): LiveData<Class<out BaseFragment>> = nextScreen

    fun showNextScreen(screen: Class<out BaseFragment>) = nextScreen.postValue(screen)

    fun getNotificationCount(text: String?): String {
        return if (text == null || text.isBlank()) {
            ""
        } else {
            text.toIntOrNull()?.inc()?.toString() ?: ""
        }
    }
}