package com.example.kotlinvkapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.example.kotlinvkapp.R
import com.example.kotlinvkapp.presenters.LoginPresenter
import com.example.kotlinvkapp.views.LoginView
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter


class LoginActivity : MvpAppCompatActivity(), LoginView {

    private lateinit var loginBtn: Button
    private lateinit var loadingBarLogin: ProgressBar

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn = findViewById(R.id.login_btn)
        loadingBarLogin = findViewById(R.id.loading_bar_login)

        loginBtn.setOnClickListener {
            VK.login(this, arrayListOf(VKScope.FRIENDS))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(!presenter.login(requestCode = requestCode, resultCode = resultCode, data = data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun openFriends() {
        startActivity(Intent(this@LoginActivity, FriendsActivity::class.java))
    }

    override fun showError(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun startLoading() {
        loadingBarLogin.visibility = View.VISIBLE
        loginBtn.visibility = View.INVISIBLE
    }

    override fun endLoading() {
        loadingBarLogin.visibility = View.INVISIBLE
        loginBtn.visibility = View.VISIBLE
    }
}