package com.login.app.ui.splash

import android.content.Intent
import android.os.Bundle
import com.login.app.ui.common.transformer.map
import com.login.app.ui.login.LoginActivity
import com.login.app.ui.profile.ProfileActivity
import com.login.helper.LoginHelper
import com.login.helper.data.model.LoggedInUser
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var loginHelper: LoginHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO need to implement profile display, if already logged in
        /*if (loginHelper.isLogin()) {
            val account = loginHelper.account()
            if (account != null) {
                onLoggedIn(account)
            }
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }*/

        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun onLoggedIn(account: LoggedInUser) {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra(ProfileActivity.EXTRA_GOOGLE_ACCOUNT, account.map())
        startActivity(intent)
        finish()
    }
}