package com.ali.advancedtask.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteUser (
    val id: Int,
    val name: String,
    val email: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val password: String
) : Parcelable