package com.ali.advancedtask.feature.home.data.model.response.trending_sellers


import com.google.gson.annotations.SerializedName

data class TrendingSellersResponseDto(
    @SerializedName("data")
    val `data`: List<TrendingSellersDataResponse>,
    @SerializedName("message")
    val message: String,
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("success")
    val success: Boolean
)