package com.login.app.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import com.login.app.ui.base.BaseActivity
import com.login.app.ui.common.extension.afterTextChanged
import com.login.app.ui.common.extension.observe
import com.login.app.ui.common.extension.viewModel
import com.login.app.ui.profile.ProfileActivity
import com.login.helper.R
import com.login.helper.core.SocialNetwork
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginViewModel>() {

    override fun buildViewModel(): LoginViewModel {
        return viewModel(viewModelFactory) {
            observe(loginFormState) { handleLoginFormState(it) }
            observe(loginResult) { handleLoginResult(it) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //TODO set test data
        setUpTestData()

        username.afterTextChanged {
            viewModel.loginDataChanged(username.text.toString(), password.text.toString())
        }

        password.apply {
            afterTextChanged {
                viewModel.loginDataChanged(username.text.toString(), password.text.toString())
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        loading.visibility = View.VISIBLE
                        viewModel.login(username.text.toString(), password.text.toString())
                    }
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                viewModel.login(username.text.toString(), password.text.toString())
            }
        }

        googlePlusSignInButton.setOnClickListener { viewModel.login(this, SocialNetwork.Google) }
        facebookSignInButton.setOnClickListener { viewModel.login(this, SocialNetwork.FaceBook) }
        twitterSignInButton.setOnClickListener {
            //TODO
            Toast.makeText(this, "need implement", Toast.LENGTH_LONG).show()
            //viewModel.login(this, SocialNetwork.FaceBook)
        }
        githubSignInButton.setOnClickListener {
            //TODO
            Toast.makeText(this, "need implement", Toast.LENGTH_LONG).show()
            //viewModel.login(this, SocialNetwork.FaceBook)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleLoginFormState(loginState: LoginFormState?) {
        if (loginState == null) return

        login.isEnabled = loginState.isDataValid
        if (loginState.usernameError != null) {
            username.error = getString(loginState.usernameError)
        }
        if (loginState.passwordError != null) {
            password.error = getString(loginState.passwordError)
        }
    }

    private fun handleLoginResult(loginResult: LoginResult?) {
        if (loginResult == null) return

        loading.visibility = View.GONE
        if (loginResult.error != null) {
            showLoginFailed(loginResult.error)
        }
        if (loginResult.success != null) {
            showProfile(loginResult.success)
        }
        setResult(Activity.RESULT_OK)
        finish()
    }

    private fun showProfile(model: LoggedInUserView) {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra(ProfileActivity.EXTRA_GOOGLE_ACCOUNT, model)
        startActivity(intent)
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    private fun setUpTestData() {
        username.text.append("testLogin")
        password.text.append("testPassword")
        viewModel.loginDataChanged(username.text.toString(), password.text.toString())
    }
}

