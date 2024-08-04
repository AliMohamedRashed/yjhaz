package com.ali.advancedtask.feature.home.data.model.response

import com.google.gson.annotations.SerializedName

data class TrendingItems(
    @SerializedName("imageUrl")
    val trendingImage: String
)