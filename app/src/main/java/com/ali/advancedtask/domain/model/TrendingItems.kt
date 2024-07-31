package com.ali.advancedtask.domain.model

import com.google.gson.annotations.SerializedName

data class TrendingItems(
    @SerializedName("imageUrl")
    val trendingImage: String
)