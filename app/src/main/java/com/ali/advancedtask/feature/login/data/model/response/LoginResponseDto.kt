package com.ali.advancedtask.feature.login.data.model.response


import com.google.gson.annotations.SerializedName

data class LoginResponseDto(
    @SerializedName("data")
    val loginDataResponse: LoginDataResponse,
    @SerializedName("message")
    val message: String,
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("success")
    val success: Boolean
)