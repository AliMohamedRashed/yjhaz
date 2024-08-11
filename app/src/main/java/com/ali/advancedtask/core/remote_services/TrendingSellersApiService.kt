package com.ali.advancedtask.core.remote_services

import com.ali.advancedtask.feature.home.data.model.response.trending_sellers.TrendingSellersResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingSellersApiService {
    @GET("api/trending-sellers")
    suspend fun getTrendingSellers(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("filter") filter: Int
    ): TrendingSellersResponseDto
}