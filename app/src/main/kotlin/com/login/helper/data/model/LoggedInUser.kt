package com.login.helper.data.model

import android.net.Uri

data class LoggedInUser(
    val email: String? = null,
    val userId: String? = null,
    val userToken: String? = null,
    val displayName: String? = null,
    val photoUrl: Uri? = null
)