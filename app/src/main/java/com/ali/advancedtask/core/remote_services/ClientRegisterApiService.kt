package com.ali.advancedtask.core.remote_services

import com.ali.advancedtask.feature.signup.data.model.request.SignUpRequestDto
import com.ali.advancedtask.feature.signup.data.model.response.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ClientRegisterApiService {
    @POST("api/client-register")
    suspend fun registerUser(@Body request: SignUpRequestDto): SignUpResponseDto

}