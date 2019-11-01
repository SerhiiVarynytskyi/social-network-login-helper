package com.login.app.ui.common.transformer

import com.login.app.ui.login.LoggedInUserView
import com.login.helper.data.model.LoggedInUser

fun LoggedInUser.map() = LoggedInUserView(
    email, userId, userToken, displayName, photoUrl
)