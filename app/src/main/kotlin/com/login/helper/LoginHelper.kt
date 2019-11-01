package com.login.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.login.helper.core.ISocialNetwork
import com.login.helper.core.OnCallBack
import com.login.helper.core.Result
import com.login.helper.core.SocialNetwork
import com.login.helper.data.model.LoggedInUser
import com.login.helper.impl.facebook.FaceBookSocialNetworkImpl
import com.login.helper.impl.google.GoogleSocialNetworkImpl
import com.login.helper.impl.ownserver.LoginDataSource
import com.login.helper.impl.ownserver.LoginRepository
import com.login.helper.impl.ownserver.OwnServerLoginImpl
import javax.inject.Inject

class LoginHelper @Inject constructor(private val context: Context) : ISocialNetwork {

    private var currentSocialNetwork: ISocialNetwork = GoogleSocialNetworkImpl(context)

    private var callBack: OnCallBack? = null

    //ISocialNetwork -----------------------------------------------------------
    override fun socialNetworkType(): SocialNetwork {
        return currentSocialNetwork.socialNetworkType()
    }

    override fun isLogin(): Boolean {
        return currentSocialNetwork.isLogin()
    }

    override fun account(): LoggedInUser? {
        return currentSocialNetwork.account()
    }

    //TODO need to implement ISwitcherSocialNetwork. Params socialNetwork temporary solution
    override fun login(activity: Activity, socialNetwork: SocialNetwork) {
        if (socialNetwork != socialNetworkType()) {
            currentSocialNetwork.setListener(null)
            currentSocialNetwork = when(socialNetwork) {
                SocialNetwork.Google -> GoogleSocialNetworkImpl(context)
                SocialNetwork.FaceBook -> FaceBookSocialNetworkImpl(context)

                else -> GoogleSocialNetworkImpl(context)
            }
            currentSocialNetwork.setListener(callBack)
        }

        currentSocialNetwork.login(activity, socialNetwork)
    }

    override fun logout(onLogout: () -> Unit) {
        currentSocialNetwork.logout { onLogout() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        currentSocialNetwork.onActivityResult(requestCode, resultCode, data)
    }

    override fun setListener(callBack: OnCallBack?) {
        this.callBack = callBack
        currentSocialNetwork.setListener(callBack)
    }

    override suspend fun login(username: String, password: String): Result<LoggedInUser> {
        currentSocialNetwork = OwnServerLoginImpl(LoginRepository(LoginDataSource()))
        return currentSocialNetwork.login(username, password)
    }

    //ISocialNetwork -----------------------------------------------------------


}