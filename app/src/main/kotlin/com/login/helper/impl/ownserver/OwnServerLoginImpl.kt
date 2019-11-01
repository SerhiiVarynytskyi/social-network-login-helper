package com.login.helper.impl.ownserver

import android.app.Activity
import android.content.Intent
import com.login.helper.core.ISocialNetwork
import com.login.helper.core.OnCallBack
import com.login.helper.core.Result
import com.login.helper.core.SocialNetwork
import com.login.helper.data.model.LoggedInUser
import javax.inject.Inject

class OwnServerLoginImpl @Inject constructor(val loginRepository: LoginRepository): ISocialNetwork {

    private var callBack: OnCallBack? = null

    override fun socialNetworkType(): SocialNetwork {
        return SocialNetwork.OwnServer
    }

    override fun isLogin(): Boolean {
        return loginRepository.isLoggedIn
    }

    override fun account(): LoggedInUser? {
        return loginRepository.user
    }

    override fun login(activity: Activity, socialNetwork: SocialNetwork) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun logout(onLogout: () -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setListener(callBack: OnCallBack?) {
        this.callBack = callBack
    }

    override suspend fun login(username: String, password: String): Result<LoggedInUser> {
        return loginRepository.login(username, password)
    }

}