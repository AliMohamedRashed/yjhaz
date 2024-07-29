package com.ali.advancedtask.data

import com.ali.advancedtask.model.category.Category
import com.ali.advancedtask.model.trending.TrendingItems
import retrofit2.http.GET

interface TrendingApiService {
    @GET("trending.json")
    suspend fun getTrendingItems(): Map<String, TrendingItems>
}