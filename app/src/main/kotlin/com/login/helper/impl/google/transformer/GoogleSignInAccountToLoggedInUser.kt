package com.login.helper.impl.google.transformer

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.login.helper.data.model.LoggedInUser

fun GoogleSignInAccount.map() = LoggedInUser(
    email = email,
    userId = id,
    userToken = idToken,
    displayName = displayName,
    photoUrl = photoUrl
)