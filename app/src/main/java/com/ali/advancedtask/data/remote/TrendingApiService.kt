package com.ali.advancedtask.data.remote

import com.ali.advancedtask.domain.model.TrendingItems
import retrofit2.http.GET

interface TrendingApiService {
    @GET("trending.json")
    suspend fun getTrendingItems(): Map<String, TrendingItems>
}