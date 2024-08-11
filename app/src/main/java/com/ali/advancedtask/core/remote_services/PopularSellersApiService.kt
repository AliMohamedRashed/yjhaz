package com.ali.advancedtask.core.remote_services

import com.ali.advancedtask.feature.home.data.model.response.popular_sellers.PopularSellersResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularSellersApiService {

    @GET("api/popular-sellers")
    suspend fun getPopularSellers(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("filter") filter: Int
    ): PopularSellersResponseDto

}