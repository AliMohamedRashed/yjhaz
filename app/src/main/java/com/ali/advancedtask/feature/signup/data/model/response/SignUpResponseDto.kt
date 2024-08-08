package com.ali.advancedtask.feature.signup.data.model.response


import com.google.gson.annotations.SerializedName

data class SignUpResponseDto(
    @SerializedName("data")
    val `data`: SignUpDataResponse?,
    @SerializedName("message")
    val message: String,
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("success")
    val success: Boolean
)