package com.login.app.ui.login

import android.app.Activity
import android.content.Intent
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.login.app.ui.base.BaseViewModel
import com.login.app.ui.common.transformer.map
import com.login.helper.LoginHelper
import com.login.helper.R
import com.login.helper.core.OnCallBack
import com.login.helper.core.Result
import com.login.helper.core.SocialNetwork
import com.login.helper.core.SocialNetworkFailure
import com.login.helper.data.model.LoggedInUser
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginHelper: LoginHelper) : BaseViewModel(),
    OnCallBack {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    init {
        loginHelper.setListener(this)
    }

    override fun onCleared() {
        super.onCleared()
        loginHelper.setListener(null)
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val result = loginHelper.login(username, password)
            if (result is Result.Success) {
                _loginResult.value = LoginResult(success = result.data.map())
            } else {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        }
    }

    fun login(activity: Activity, socialNetwork: SocialNetwork) {
        loginHelper.login(activity, socialNetwork)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loginHelper.onActivityResult(requestCode, resultCode, data)
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    //OnCallBack ---------------------------------------------------------------
    override fun onLogin(account: LoggedInUser) {
        _loginResult.value = LoginResult(success = account.map())
    }

    override fun onFailure(failure: SocialNetworkFailure) {
        _loginResult.value = LoginResult(error = R.string.login_failed)
    }
    //--------------------------------------------------------------------------
}
