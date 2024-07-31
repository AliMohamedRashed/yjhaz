package com.ali.advancedtask.data.repository.interfaces

import com.ali.advancedtask.domain.model.TrendingItems

interface TrendingRepository {

    suspend fun getAllTrendingItems(): List<TrendingItems>
}