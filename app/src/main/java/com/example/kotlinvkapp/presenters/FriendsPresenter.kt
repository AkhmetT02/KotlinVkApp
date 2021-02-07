package com.example.kotlinvkapp.presenters

import com.example.kotlinvkapp.models.FriendModel
import com.example.kotlinvkapp.providers.FriendsProvider
import com.example.kotlinvkapp.views.FriendsView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class FriendsPresenter: MvpPresenter<FriendsView>() {

    fun loadFriends() {
        viewState.startLoading()
        FriendsProvider(presenter = this).loadFriends()
    }

    fun friendsLoadFinished(friends: ArrayList<FriendModel>) {
        viewState.endLoading()
        if (friends.size == 0) {
            viewState.setupEmptyList()
            viewState.showError("You haven't friends :(")
        } else {
            viewState.setupFriendsList(friends = friends)
        }
    }

    fun showError(text: String) {
        viewState.showError(text)
    }
}