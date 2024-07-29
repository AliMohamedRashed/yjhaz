package com.ali.advancedtask.model.trending

import com.ali.advancedtask.data.TrendingApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TrendingRepository {

    private val apiService: TrendingApiService = Retrofit.Builder()
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .baseUrl("https://yjhaz-495b5-default-rtdb.firebaseio.com/")
        .build()
        .create(TrendingApiService::class.java)

    suspend fun getAllTrendingItems() = withContext(Dispatchers.IO) {
        return@withContext apiService.getTrendingItems().values.toList()
    }

}