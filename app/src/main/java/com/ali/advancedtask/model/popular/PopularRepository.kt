package com.ali.advancedtask.model.popular

import com.ali.advancedtask.data.PopularApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PopularRepository {

    private val apiService: PopularApiService = Retrofit.Builder()
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .baseUrl("https://yjhaz-495b5-default-rtdb.firebaseio.com/")
        .build()
        .create(PopularApiService::class.java)

    suspend fun getAllPopularItems() = withContext(Dispatchers.IO) {
        return@withContext apiService.getPopularItems().values.toList()
    }

}