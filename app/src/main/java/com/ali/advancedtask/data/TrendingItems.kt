package com.ali.advancedtask.data
import com.ali.advancedtask.R

val trendingItems = listOf(
    TrendingItems(R.drawable.mac),
    TrendingItems(R.drawable.kdc),
    TrendingItems(R.drawable.hardees)
)
data class TrendingItems (val trendingImage: Int)