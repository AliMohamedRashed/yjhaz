package com.ali.advancedtask.model

import android.os.Parcelable

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    var id: Int? = null,
    val name: String,
    val email: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val password: String
) : Parcelable