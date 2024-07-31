package com.ali.advancedtask.data.repository

import com.ali.advancedtask.data.remote.PopularApiService
import com.ali.advancedtask.data.repository.interfaces.PopularRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val apiService: PopularApiService
): PopularRepository {

    override
    suspend fun getAllPopularItems() = withContext(Dispatchers.IO) {
       apiService.getPopularItems().values.toList()
    }

}