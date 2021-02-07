package com.example.kotlinvkapp.presenters

import android.content.Intent
import com.example.kotlinvkapp.views.LoginView
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class LoginPresenter: MvpPresenter<LoginView>() {

    fun login(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        viewState.startLoading()
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, object: VKAuthCallback {
                override fun onLogin(token: VKAccessToken) {
                    viewState.openFriends()
                }

                override fun onLoginFailed(errorCode: Int) {
                    viewState.showError("Login is failed...")
                }
            })) {
            viewState.endLoading()
            return false
        }
        viewState.endLoading()
        return true
    }


}