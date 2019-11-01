package com.login.helper.core

import android.app.Activity
import android.content.Intent
import com.login.helper.data.model.LoggedInUser

enum class SocialNetwork {
    OwnServer,
    Google,
    FaceBook,
}

interface ISocialNetwork {

    fun socialNetworkType(): SocialNetwork
    fun isLogin(): Boolean
    fun account(): LoggedInUser?

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        return Result.Failure(SocialNetworkFailure.NetworkConnection)
    }

    fun login(activity: Activity, socialNetwork: SocialNetwork)
    fun logout(onLogout: () -> Unit)
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    fun setListener(callBack: OnCallBack?)
}

interface ISwitcherSocialNetwork {

    fun switchSocialNetwork(socialNetwork: SocialNetwork)

}

sealed class SocialNetworkFailure {
    object NotInternetConnected : SocialNetworkFailure()
    object NetworkConnection : SocialNetworkFailure()
    object ServerError : SocialNetworkFailure()

    abstract class FeatureFailure: SocialNetworkFailure()

    data class CustomFailure(val exception: Throwable) : FeatureFailure()
}

interface OnCallBack {
    fun onLogin(account: LoggedInUser)
    fun onFailure(failure: SocialNetworkFailure)
}