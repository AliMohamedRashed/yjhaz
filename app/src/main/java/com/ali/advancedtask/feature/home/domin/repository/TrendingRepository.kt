package com.ali.advancedtask.feature.home.domin.repository

import com.ali.advancedtask.feature.home.data.model.response.TrendingItems

interface TrendingRepository {

    suspend fun getAllTrendingItems(): List<TrendingItems>
}