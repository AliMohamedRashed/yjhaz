package com.ali.advancedtask.data.repository

import com.ali.advancedtask.data.remote.CategoriesApiService
import com.ali.advancedtask.data.repository.interfaces.CategoriesRepository
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