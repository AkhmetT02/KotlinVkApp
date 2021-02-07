package com.example.kotlinvkapp.views

import com.example.kotlinvkapp.models.FriendModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FriendsView: MvpView {
    fun showError(text: String)
    fun setupEmptyList()
    fun setupFriendsList(friends: ArrayList<FriendModel>)
    fun startLoading()
    fun endLoading()
}