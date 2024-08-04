package com.ali.advancedtask.feature.home.data.repository

import com.ali.advancedtask.core.remote_services.PopularApiService
import com.ali.advancedtask.feature.home.domin.repository.PopularRepository
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