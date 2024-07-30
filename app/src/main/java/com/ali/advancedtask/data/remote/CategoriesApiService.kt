package com.ali.advancedtask.data.remote

import com.ali.advancedtask.model.Category
import retrofit2.http.GET

interface CategoriesApiService {
    @GET("categories.json")
    suspend fun getCategories(): Map<String, Category>
}