package com.ali.advancedtask.data

import com.ali.advancedtask.data.remote.TrendingApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class TrendingRepository  @Inject constructor(
    private val apiService: TrendingApiService
){
    suspend fun getAllTrendingItems() = withContext(Dispatchers.IO) {
        return@withContext apiService.getTrendingItems().values.toList()
    }

}