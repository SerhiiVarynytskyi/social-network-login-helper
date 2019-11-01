package com.login.app.ui.login

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

data class LoggedInUserView(
    val email: String? = null,
    val userId: String? = null,
    val userToken: String? = null,
    val displayName: String? = null,
    val photoUrl: Uri? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Uri::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(userId)
        parcel.writeString(userToken)
        parcel.writeString(displayName)
        parcel.writeParcelable(photoUrl, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoggedInUserView> {
        override fun createFromParcel(parcel: Parcel): LoggedInUserView {
            return LoggedInUserView(parcel)
        }

        override fun newArray(size: Int): Array<LoggedInUserView?> {
            return arrayOfNulls(size)
        }
    }
}
