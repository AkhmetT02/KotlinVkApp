package com.example.kotlinvkapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinvkapp.R
import com.example.kotlinvkapp.models.FriendModel
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class FriendAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mSourceList: ArrayList<FriendModel> = ArrayList()
    private val friends: ArrayList<FriendModel> = ArrayList()

    fun setupFriends(friends: ArrayList<FriendModel>) {
        this.mSourceList.clear()
        this.mSourceList.addAll(friends)
        filter(query = "")
    }
    fun filter(query: String) {
        friends.clear()
        mSourceList.forEach {
            if (it.name.contains(query, ignoreCase = true) || it.surname.contains(query, ignoreCase = true)) {
                friends.add(it)
            } else {
                it.city?.let { city ->
                    if (city.contains(query, ignoreCase = true)) {
                        friends.add(it)
                    }
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.friend_item, parent, false)

        return FriendViewHolder(view)
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FriendViewHolder) {
            holder.bind(friendModel = friends[position])
        }
    }

    inner class FriendViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var mTxtName: TextView = itemView.findViewById(R.id.friend_name_tv)
        private var mTxtCity: TextView = itemView.findViewById(R.id.friend_city_tv)
        private var mImgOnline: View = itemView.findViewById(R.id.friend_online_img)
        private var mCivAvatar: CircleImageView = itemView.findViewById(R.id.friend_image)

        fun bind(friendModel: FriendModel) {
            Picasso.get().load(friendModel.avatar).into(mCivAvatar)
            mTxtName.text = "${friendModel.name} ${friendModel.surname}"
            mTxtCity.text = friendModel.city

            if (friendModel.isOnline) {
                mImgOnline.visibility = View.VISIBLE
            } else {
                mImgOnline.visibility = View.INVISIBLE
            }
        }
    }
}