package com.ali.advancedtask.feature.login.domin.repository

import com.ali.advancedtask.core.State
import com.ali.advancedtask.feature.login.data.model.request.LoginRequestDto
import com.ali.advancedtask.feature.login.data.model.response.LoginResponseDto
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun loginUser(request: LoginRequestDto): Flow<State<LoginResponseDto>>

    suspend fun cacheUserData(loginData: LoginResponseDto)
}