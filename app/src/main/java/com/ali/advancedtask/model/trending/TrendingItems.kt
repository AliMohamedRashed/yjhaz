package com.ali.advancedtask.model.trending
import com.google.gson.annotations.SerializedName

//
//val trendingItems = listOf(
//    TrendingItems(R.drawable.mac),
//    TrendingItems(R.drawable.kdc),
//    TrendingItems(R.drawable.hardees)
//)

data class TrendingItems (
    @SerializedName("imageUrl")
    val trendingImage: String
)