package com.ali.advancedtask.feature.home.data.repository

import com.ali.advancedtask.core.remote_services.BaseCategoriesApiService
import com.ali.advancedtask.core.remote_services.PopularSellersApiService
import com.ali.advancedtask.core.remote_services.TrendingSellersApiService
import com.ali.advancedtask.feature.home.data.model.response.base_categories.BaseCategoriesResponseDto
import com.ali.advancedtask.feature.home.data.model.response.popular_sellers.PopularSellersResponseDto
import com.ali.advancedtask.feature.home.data.model.response.trending_sellers.TrendingSellersResponseDto
import com.ali.advancedtask.feature.home.domin.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val baseCategoriesApiService: BaseCategoriesApiService,
    private val popularSellersApiService: PopularSellersApiService,
    private val trendingSellersApiService: TrendingSellersApiService
): HomeRepository {
    override suspend fun getCategories(): BaseCategoriesResponseDto =
        withContext(Dispatchers.IO){
            return@withContext baseCategoriesApiService.getCategories()
        }

    override suspend fun getPopularSellers(lat: Double, lng: Double,filter: Int): PopularSellersResponseDto =
        withContext(Dispatchers.IO){
            return@withContext popularSellersApiService.getPopularSellers(
                lat = lat,
                lng = lng,
                filter = filter
            )
        }

    override suspend fun getTrendingSellers(lat: Double, lng: Double, filter: Int): TrendingSellersResponseDto =
        withContext(Dispatchers.IO){
            return@withContext trendingSellersApiService.getTrendingSellers(
                lat = lat,
                lng = lng,
                filter = filter
            )
        }
}