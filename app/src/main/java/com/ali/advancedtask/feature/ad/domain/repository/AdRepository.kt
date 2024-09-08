package com.ali.advancedtask.feature.ad.domain.repository

import com.ali.advancedtask.core.State
import com.ali.advancedtask.feature.ad.data.model.AdDetails
import kotlinx.coroutines.flow.Flow

interface AdRepository {
    suspend fun getAdDetails(): Flow<State<AdDetails>>
}