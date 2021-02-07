package com.example.kotlinvkapp.models

data class FriendModel(
    val name: String,
    val surname: String,
    val avatar: String?,
    val city: String?,
    val isOnline: Boolean
)