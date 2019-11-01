package com.login.app.ui.profile

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.login.app.ui.login.LoggedInUserView
import com.login.app.ui.login.LoginActivity
import com.login.helper.LoginHelper
import com.login.helper.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*
import javax.inject.Inject


class ProfileActivity : DaggerAppCompatActivity() {

    companion object {
        const val EXTRA_GOOGLE_ACCOUNT = "com.login.helper.GOOGLE_ACCOUNT"
    }

    @Inject
    lateinit var loginHelper: LoginHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val googleSignInAccount =
            intent.getParcelableExtra<LoggedInUserView>(EXTRA_GOOGLE_ACCOUNT)

        profileName.text = googleSignInAccount?.displayName
        profileEmail.text = googleSignInAccount?.email
        Glide.with(this).load(googleSignInAccount?.photoUrl).into(profileImage)

        signOut.setOnClickListener {
            loginHelper.logout {
                val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }
}