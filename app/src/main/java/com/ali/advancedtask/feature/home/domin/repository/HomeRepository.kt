package com.ali.advancedtask.feature.home.domin.repository

import com.ali.advancedtask.feature.home.data.model.response.base_categories.BaseCategoriesResponseDto
import com.ali.advancedtask.feature.home.data.model.response.popular_sellers.PopularSellersResponseDto
import com.ali.advancedtask.feature.home.data.model.response.trending_sellers.TrendingSellersResponseDto


interface HomeRepository {

    suspend fun getCategories(): BaseCategoriesResponseDto

    suspend fun getPopularSellers(lat: Double, lng: Double,filter: Int): PopularSellersResponseDto

    suspend fun getTrendingSellers(lat: Double, lng: Double,filter: Int): TrendingSellersResponseDto
}