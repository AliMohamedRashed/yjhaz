package com.ali.advancedtask.model.trending
import com.ali.advancedtask.R

val trendingItems = listOf(
    TrendingItems(R.drawable.mac),
    TrendingItems(R.drawable.kdc),
    TrendingItems(R.drawable.hardees)
)
data class TrendingItems (val trendingImage: Int)