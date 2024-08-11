package com.ali.advancedtask.core.remote_services

import com.ali.advancedtask.feature.login.data.model.request.LoginRequestDto
import com.ali.advancedtask.feature.login.data.model.response.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {

    @POST("api/login")
    suspend fun getUser(@Body request: LoginRequestDto): LoginResponseDto

}