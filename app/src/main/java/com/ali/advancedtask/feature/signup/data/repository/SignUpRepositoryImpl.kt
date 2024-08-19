package com.ali.advancedtask.feature.signup.data.repository

import com.ali.advancedtask.core.State
import com.ali.advancedtask.core.extension_functions.asResult
import com.ali.advancedtask.core.remote_services.ClientRegisterApiService
import com.ali.advancedtask.core.user_manager.UserHandler
import com.ali.advancedtask.feature.signup.data.model.request.SignUpRequestDto
import com.ali.advancedtask.feature.signup.data.model.response.SignUpResponseDto
import com.ali.advancedtask.feature.signup.domain.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val apiService: ClientRegisterApiService,
    private val userHandler: UserHandler,
) : SignUpRepository {
    override suspend fun registerUser(request: SignUpRequestDto): Flow<State<SignUpResponseDto>> {
        return flow {
            val response = apiService.registerUser(request)
            if(response.success) cacheUserData(response)
            emit(response)
        }.asResult()
    }

    override suspend fun cacheUserData(registerData: SignUpResponseDto) {
        userHandler.run {
            setUserToken(registerData.data!!.token)
            setUserName(registerData.data.name)
        }
    }


}