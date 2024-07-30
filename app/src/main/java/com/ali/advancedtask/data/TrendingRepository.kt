package com.ali.advancedtask.data

import com.ali.advancedtask.data.remote.TrendingApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrendingRepository  @Inject constructor(
    private val apiService: TrendingApiService
){
    suspend fun getAllTrendingItems() = withContext(Dispatchers.IO) {
        apiService.getTrendingItems().values.toList()
    }

}