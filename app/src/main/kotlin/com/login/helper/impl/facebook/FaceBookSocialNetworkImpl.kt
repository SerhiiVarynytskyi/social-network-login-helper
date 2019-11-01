package com.login.helper.impl.facebook

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.login.helper.core.ISocialNetwork
import com.login.helper.core.OnCallBack
import com.login.helper.core.SocialNetwork
import com.login.helper.data.model.LoggedInUser
import com.login.helper.impl.facebook.transformer.map
import org.json.JSONObject
import javax.inject.Inject


class FaceBookSocialNetworkImpl @Inject constructor(private val context: Context) : ISocialNetwork,
    FacebookCallback<LoginResult> {

    companion object {
        private const val TAG = "GoogleSocialNetworkImpl"

        private const val BASE_URL = "https://graph.facebook.com/"
        private const val URL_PICTURE_PARAMS = "/picture?width=250&height=250"
        private const val FIELDS = "fields"
        private const val KEY_ID = "id"
        private const val KEY_FIRST_NAME = "first_name"
        private const val KEY_LAST_NAME = "last_name"
        private const val KEY_EMAIL = "email"
    }

    private var callBack: OnCallBack? = null
    private lateinit var callbackManager: CallbackManager

    init {
        //FB
        val applicationContext = context.applicationContext
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(applicationContext as Application)
        callbackManager = CallbackManager.Factory.create()

        val isLoggedIn = isLogin()
        //TODO
        if (isLoggedIn) {
            //val permissionNeeds = listOf("email") //"public_profile"
            //LoginManager.getInstance().logInWithReadPermissions(activity, permissionNeeds)
        }
    }

    override fun socialNetworkType(): SocialNetwork {
        return SocialNetwork.FaceBook
    }

    override fun isLogin(): Boolean {
        val accessToken = AccessToken.getCurrentAccessToken()
        return accessToken != null && !accessToken.isExpired
    }

    override fun account(): LoggedInUser? {
        if (isLogin()) {
            val profile = Profile.getCurrentProfile()
            return profile?.map()
        }
        return null
    }

    override fun login(activity: Activity, socialNetwork: SocialNetwork) {
        LoginManager.getInstance().logOut()
        val permissionNeeds = listOf("email") //"public_profile"
        LoginManager.getInstance().logInWithReadPermissions(activity, permissionNeeds)
    }

    override fun logout(onLogout: () -> Unit) {
        LoginManager.getInstance().logOut()
        onLogout()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun setListener(callBack: OnCallBack?) {
        this.callBack = callBack
        if (callBack == null) {
            LoginManager.getInstance().unregisterCallback(callbackManager)
        } else {
            LoginManager.getInstance().registerCallback(callbackManager, this)
        }
    }

    private fun onSuccessFacebookLogin(result: LoginResult) {

        val request = GraphRequest.newMeRequest(result.accessToken) { obj, _ ->
            if (obj != null) {
                callBack?.onLogin(getData(obj, result.accessToken.token))
                try {
                } catch (e: Exception) {
                    Log.e(TAG, e.message, e)
                }
            }
        }
        val parameters = Bundle()
        parameters.putString(FIELDS, "${KEY_ID},${KEY_FIRST_NAME},${KEY_LAST_NAME},${KEY_EMAIL}")
        request.parameters = parameters
        request.executeAsync()
    }



    //implement FacebookCallback<LoginResult> ----------------------------------
    override fun onSuccess(result: LoginResult?) {
        if (result != null) {
            onSuccessFacebookLogin(result)
        }
    }

    override fun onCancel() {

    }

    override fun onError(error: FacebookException?) {

    }
    //FacebookCallback<LoginResult> --------------------------------------------

    private fun getData(obj: JSONObject, token: String): LoggedInUser {
        val id = obj.getString(KEY_ID)
        val email = obj.getString(KEY_EMAIL)
        val firstName = obj.getString(KEY_FIRST_NAME)
        val lastName = obj.getString(KEY_LAST_NAME)
        val picture = Uri.parse(BASE_URL + id + URL_PICTURE_PARAMS)
        return LoggedInUser(
            email = email,
            userId = id,
            userToken = token,
            displayName = "$firstName $lastName",
            photoUrl = picture
        )
    }

}