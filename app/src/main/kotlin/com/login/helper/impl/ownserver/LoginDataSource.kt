package com.login.helper.impl.ownserver

import com.login.helper.core.Result
import com.login.helper.core.SocialNetworkFailure
import com.login.helper.data.model.LoggedInUser
import java.io.IOException
import java.util.concurrent.TimeUnit

class LoginDataSource {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        return try {
            // TODO: handle loggedInUser authentication
            TimeUnit.SECONDS.sleep(3L)
            val fakeUser = LoggedInUser(
                email = "test@gmail.com",
                userId = java.util.UUID.randomUUID().toString(),
                userToken = java.util.UUID.randomUUID().toString(),
                displayName = "Test user displayName"
            )
            Result.Success(fakeUser)
        } catch (e: Throwable) {
            Result.Failure(
                SocialNetworkFailure.CustomFailure(IOException("Error logging in", e))
            )
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

