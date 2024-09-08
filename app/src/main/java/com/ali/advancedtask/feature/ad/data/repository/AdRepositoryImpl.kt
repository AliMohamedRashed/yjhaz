package com.ali.advancedtask.feature.ad.data.repository

import com.ali.advancedtask.core.State
import com.ali.advancedtask.feature.ad.data.model.AdDetails
import com.ali.advancedtask.feature.ad.domain.repository.AdRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AdRepositoryImpl : AdRepository {
    override suspend fun getAdDetails(): Flow<State<AdDetails>> = flow {
        emit(State.Loading)
        try {
            val ad = AdDetails("Etoile", "خالد عليش _ ايتوال عملت كنافة",
                "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4")
            emit(State.Success(ad))
        } catch (e: Exception) {
            emit(State.Error(e))
        }
    }
}