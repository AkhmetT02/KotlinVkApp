package com.example.kotlinvkapp.providers

import com.example.kotlinvkapp.models.FriendModel
import com.example.kotlinvkapp.presenters.FriendsPresenter
import com.example.kotlinvkapp.vkhelpers.FriendsRequest
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback

class FriendsProvider(private val presenter: FriendsPresenter) {

    fun loadFriends() {

        VK.execute(FriendsRequest(), object: VKApiCallback<List<FriendModel>> {
            override fun success(result: List<FriendModel>) {
                presenter.friendsLoadFinished(result as ArrayList<FriendModel>)
            }
            override fun fail(error: Exception) {
                presenter.showError(error.message!!)
            }
        })
    }
}