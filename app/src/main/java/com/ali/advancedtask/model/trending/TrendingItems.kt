package com.ali.advancedtask.model.trending
import com.google.gson.annotations.SerializedName

data class TrendingItems (
    @SerializedName("imageUrl")
    val trendingImage: String
)