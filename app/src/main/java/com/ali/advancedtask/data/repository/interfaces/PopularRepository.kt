package com.ali.advancedtask.data.repository.interfaces

import com.ali.advancedtask.domain.model.PopularItem

interface PopularRepository {

    suspend fun getAllPopularItems(): List<PopularItem>
}