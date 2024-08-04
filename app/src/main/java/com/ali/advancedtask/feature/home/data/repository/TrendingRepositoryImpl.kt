package com.ali.advancedtask.feature.home.data.repository

import com.ali.advancedtask.core.remote_services.TrendingApiService
import com.ali.advancedtask.feature.home.domin.repository.TrendingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrendingRepositoryImpl  @Inject constructor(
    private val apiService: TrendingApiService
): TrendingRepository {

    override
    suspend fun getAllTrendingItems() = withContext(Dispatchers.IO) {
        apiService.getTrendingItems().values.toList()
    }

}