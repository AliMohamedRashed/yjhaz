package com.ali.advancedtask.feature.signup.domain.repository

import com.ali.advancedtask.core.State
import com.ali.advancedtask.feature.signup.data.model.request.SignUpRequestDto
import com.ali.advancedtask.feature.signup.data.model.response.SignUpResponseDto
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {

    suspend fun registerUser(request: SignUpRequestDto): Flow<State<SignUpResponseDto>>

    suspend fun cacheUserData(registerData: SignUpResponseDto)

}