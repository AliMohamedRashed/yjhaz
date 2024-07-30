package com.ali.advancedtask.data

import com.ali.advancedtask.data.remote.PopularApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class PopularRepository @Inject constructor(
    private val apiService: PopularApiService
){

    suspend fun getAllPopularItems() = withContext(Dispatchers.IO) {
        return@withContext apiService.getPopularItems().values.toList()
    }

}