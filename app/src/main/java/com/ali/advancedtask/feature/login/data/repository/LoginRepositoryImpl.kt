package com.ali.advancedtask.feature.login.data.repository

import com.ali.advancedtask.core.remote_services.LoginApiService
import com.ali.advancedtask.feature.login.data.model.request.LoginRequestDto
import com.ali.advancedtask.feature.login.data.model.response.LoginResponseDto
import com.ali.advancedtask.feature.login.domin.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: LoginApiService
) : LoginRepository {
    override suspend fun loginUser(request: LoginRequestDto): LoginResponseDto =
        withContext(Dispatchers.IO){
            return@withContext apiService.getUser(request)
        }
}