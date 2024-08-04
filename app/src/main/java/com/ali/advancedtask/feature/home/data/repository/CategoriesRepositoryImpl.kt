package com.ali.advancedtask.feature.home.data.repository

import com.ali.advancedtask.core.remote_services.CategoriesApiService
import com.ali.advancedtask.feature.home.domin.repository.CategoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: CategoriesApiService
): CategoriesRepository {

    override
    suspend fun getAllCategories() = withContext(Dispatchers.IO) {
        apiService.getCategories().values.toList()
    }
}