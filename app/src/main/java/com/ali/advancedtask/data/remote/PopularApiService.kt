package com.ali.advancedtask.data.remote

import com.ali.advancedtask.domain.model.PopularItem
import retrofit2.http.GET

interface PopularApiService {

    @GET("popularItems.json")
    suspend fun getPopularItems(): Map<String, PopularItem>

}