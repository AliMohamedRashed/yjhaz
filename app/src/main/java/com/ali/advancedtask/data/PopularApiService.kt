package com.ali.advancedtask.data

import com.ali.advancedtask.model.popular.PopularItem
import retrofit2.http.GET

interface PopularApiService {

    @GET("popularItems.json")
    suspend fun getPopularItems(): Map<String, PopularItem>

}