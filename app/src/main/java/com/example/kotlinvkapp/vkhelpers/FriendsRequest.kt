package com.example.kotlinvkapp.vkhelpers

import android.util.Log
import com.example.kotlinvkapp.models.FriendModel
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class FriendsRequest: VKRequest<List<FriendModel>> {
    constructor(): super("friends.get") {
        addParam("fields", arrayOf("photo_100", "city", "online"))
    }

    override fun parse(r: JSONObject): List<FriendModel> {
        val friendsList = ArrayList<FriendModel>()

        val users = r.getJSONObject("response").getJSONArray("items")

        Log.i("FriendListRequestTag", users.toString())

        for (i in 0 until users.length()) {
            val jsonFriend = users.getJSONObject(i)

            val city = if (jsonFriend.has("city")) {
                jsonFriend.getJSONObject("city").getString("title")
            } else {
                ""
            }
            val friendModel = FriendModel(
                name = jsonFriend.getString("first_name"),
                surname = jsonFriend.getString("last_name"),
                avatar = jsonFriend.getString("photo_100"),
                city = city,
                isOnline = jsonFriend.getInt("online") == 1
            )
            friendsList.add(friendModel)
        }


        return friendsList
    }
}