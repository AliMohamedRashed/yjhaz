package com.ali.advancedtask.core.remote_services

import com.ali.advancedtask.feature.home.data.model.response.base_categories.BaseCategoriesResponseDto
import retrofit2.http.GET

interface BaseCategoriesApiService {
    @GET("api/base-categories")
    suspend fun getCategories(): BaseCategoriesResponseDto
}