package com.example.kotlinvkapp.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinvkapp.R
import com.example.kotlinvkapp.adapters.FriendAdapter
import com.example.kotlinvkapp.models.FriendModel
import com.example.kotlinvkapp.presenters.FriendsPresenter
import com.example.kotlinvkapp.views.FriendsView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class FriendsActivity : MvpAppCompatActivity(), FriendsView {

    private lateinit var loadingBar: ProgressBar
    private lateinit var recyclerFriends: RecyclerView
    private lateinit var notHaveFriends: TextView
    private lateinit var friendsSearch: EditText

    private val friendAdapter = FriendAdapter()

    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        loadingBar = findViewById(R.id.loading_bar_friends)
        notHaveFriends = findViewById(R.id.not_have_friends)
        friendsSearch = findViewById(R.id.friends_search_et)
        recyclerFriends = findViewById(R.id.recycler_friends)

        friendsPresenter.loadFriends()

        friendsSearch.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                friendAdapter.filter(query = s.toString())
            }
        })

        recyclerFriends.adapter = friendAdapter
        recyclerFriends.layoutManager = LinearLayoutManager(this)
    }


    override fun showError(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun setupEmptyList() {
        notHaveFriends.visibility = View.VISIBLE
        recyclerFriends.visibility = View.INVISIBLE
    }

    override fun setupFriendsList(friends: ArrayList<FriendModel>) {
        notHaveFriends.visibility = View.INVISIBLE
        recyclerFriends.visibility = View.VISIBLE
        friendAdapter.setupFriends(friends)
    }

    override fun startLoading() {
        loadingBar.visibility = View.VISIBLE
        recyclerFriends.visibility = View.INVISIBLE
    }

    override fun endLoading() {
        loadingBar.visibility = View.INVISIBLE
        recyclerFriends.visibility = View.VISIBLE
    }
}