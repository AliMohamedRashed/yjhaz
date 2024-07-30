package com.ali.advancedtask.data

import com.ali.advancedtask.data.remote.PopularApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PopularRepository @Inject constructor(
    private val apiService: PopularApiService
){

    suspend fun getAllPopularItems() = withContext(Dispatchers.IO) {
       apiService.getPopularItems().values.toList()
    }

}