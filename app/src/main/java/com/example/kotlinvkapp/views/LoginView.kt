package com.example.kotlinvkapp.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface LoginView: MvpView {
    fun openFriends()
    fun showError(text: String)
    fun startLoading()
    fun endLoading()
}