package com.login.helper.impl.facebook.transformer

import com.facebook.Profile
import com.login.helper.data.model.LoggedInUser

fun Profile.map() = LoggedInUser(
    email = null,
    userId = id,
    userToken = null,
    displayName = name,
    photoUrl = linkUri
)
