package com.ali.advancedtask.model.category

import com.ali.advancedtask.data.CategoriesApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoriesRepository {

    private val apiService: CategoriesApiService = Retrofit.Builder()
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .baseUrl("https://yjhaz-495b5-default-rtdb.firebaseio.com/")
        .build()
        .create(CategoriesApiService::class.java)

    suspend fun getAllCategories() = withContext(Dispatchers.IO) {
        return@withContext apiService.getCategories().values.toList()
    }
}