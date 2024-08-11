package com.ali.advancedtask.feature.login.data.model.response


import com.google.gson.annotations.SerializedName

data class LoginDataResponse(

    @SerializedName("addresses")
    val addresses: List<Any>,
    @SerializedName("balance")
    val balance: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("token")
    val token: String,
    @SerializedName("type")
    val type: Int

)