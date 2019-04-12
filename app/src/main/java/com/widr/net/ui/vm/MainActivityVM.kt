package com.widr.net.ui.vm

import android.arch.lifecycle.LiveData
import com.widr.net.AndroidApplication
import com.widr.net.data_flow.interactions.ITokenInteractor
import com.widr.net.ui.base.BaseFragment
import com.widr.net.ui.base.BaseViewModel
import com.widr.net.ui.fragment.LoadingFragment
import com.widr.net.ui.fragment.LoginFragment
import com.widr.net.utils.SingleLiveEvent
import timber.log.Timber
import javax.inject.Inject

class MainActivityVM : BaseViewModel() {

    @Inject lateinit var tokenInteractor: ITokenInteractor

    private val nextScreen: SingleLiveEvent<Class<out BaseFragment>> = SingleLiveEvent()

    init {
        AndroidApplication.component.inject(this)
    }

    fun getNextScreen(): LiveData<Class<out BaseFragment>> = nextScreen

    fun checkIfUserAlreadyLogin() {
        if (tokenInteractor.hasPassword().and(tokenInteractor.hasUserName())) {
            Timber.d("User already login")
            nextScreen.postValue(LoadingFragment::class.java)
        } else {
            nextScreen.postValue(LoginFragment::class.java)
        }
    }
}