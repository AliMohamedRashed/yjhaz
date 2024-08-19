package com.ali.advancedtask.feature.home.data.model.response.popular_sellers


import com.google.gson.annotations.SerializedName

data class PopularSellersResponseDto(
    @SerializedName("data")
    val `data`: List<PopularSellersDataResponse>,
    @SerializedName("message")
    val message: String,
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("success")
    val success: Boolean
)