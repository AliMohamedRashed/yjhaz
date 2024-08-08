package com.ali.advancedtask.feature.login.domin.repository

import com.ali.advancedtask.feature.login.data.model.request.LoginRequestDto
import com.ali.advancedtask.feature.login.data.model.response.LoginResponseDto

interface LoginRepository {

    suspend fun loginUser(request: LoginRequestDto): LoginResponseDto
}