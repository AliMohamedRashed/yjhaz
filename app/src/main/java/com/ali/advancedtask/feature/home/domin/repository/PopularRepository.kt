package com.ali.advancedtask.feature.home.domin.repository

import com.ali.advancedtask.feature.home.data.model.response.PopularItem

interface PopularRepository {

    suspend fun getAllPopularItems(): List<PopularItem>
}