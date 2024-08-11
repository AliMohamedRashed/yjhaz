package com.ali.advancedtask.feature.home.data.model.response.base_categories


import com.google.gson.annotations.SerializedName

data class BaseCategoriesResponseDto(
    @SerializedName("data")
    val data: List<BaseCategoriesDataResponse>?,
    @SerializedName("message")
    val message: String,
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("success")
    val success: Boolean
)