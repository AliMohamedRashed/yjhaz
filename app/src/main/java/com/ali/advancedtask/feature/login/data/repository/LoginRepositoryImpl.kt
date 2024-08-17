package com.ali.advancedtask.feature.login.data.repository

import com.ali.advancedtask.core.State
import com.ali.advancedtask.core.extension_functions.asResult
import com.ali.advancedtask.core.remote_services.LoginApiService
import com.ali.advancedtask.feature.login.data.model.request.LoginRequestDto
import com.ali.advancedtask.feature.login.data.model.response.LoginResponseDto
import com.ali.advancedtask.feature.login.domin.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: LoginApiService
) : LoginRepository {
    override suspend fun loginUser(request: LoginRequestDto): Flow<State<LoginResponseDto>> {
        return flow {
            val response = apiService.getUser(request)
            emit(response)
        }.asResult()
    }

}