package com.ali.advancedtask.data

import com.ali.advancedtask.data.remote.CategoriesApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val apiService: CategoriesApiService
) {

    suspend fun getAllCategories() = withContext(Dispatchers.IO) {
        return@withContext apiService.getCategories().values.toList()
    }
}