package com.ali.advancedtask.data.repository

import com.ali.advancedtask.data.remote.TrendingApiService
import com.ali.advancedtask.data.repository.interfaces.TrendingRepository
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