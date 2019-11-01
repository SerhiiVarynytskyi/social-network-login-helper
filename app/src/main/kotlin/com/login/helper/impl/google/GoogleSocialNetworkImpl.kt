package com.login.helper.impl.google

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.login.helper.core.ISocialNetwork
import com.login.helper.core.OnCallBack
import com.login.helper.core.SocialNetwork
import com.login.helper.core.SocialNetworkFailure
import com.login.helper.data.model.LoggedInUser
import com.login.helper.impl.google.transformer.map
import javax.inject.Inject

class GoogleSocialNetworkImpl @Inject constructor(private val context: Context) : ISocialNetwork {

    companion object {
        private const val TAG = "GoogleSocialNetworkImpl"
        private const val RC_SIGN_IN = 0x0100
    }

    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    private val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)

    private var callBack: OnCallBack? = null

    override fun socialNetworkType(): SocialNetwork {
        return SocialNetwork.Google
    }

    override fun isLogin(): Boolean {
        return account() != null
    }

    override fun account(): LoggedInUser? {
        return GoogleSignIn.getLastSignedInAccount(context)?.map()
    }

    override fun login(activity: Activity, socialNetwork: SocialNetwork) {
        val signInIntent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun logout(onLogout: () -> Unit) {
        googleSignInClient.signOut().addOnCompleteListener { onLogout() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RC_SIGN_IN) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task)
            }
        }
    }

    override fun setListener(callBack: OnCallBack?) {
        this.callBack = callBack
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                callBack?.onLogin(account.map())
            } else {
                callBack?.onFailure(SocialNetworkFailure.ServerError)
            }
        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            callBack?.onFailure(SocialNetworkFailure.CustomFailure(e))
        }
    }

}