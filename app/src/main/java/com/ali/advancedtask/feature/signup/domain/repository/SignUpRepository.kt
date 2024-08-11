package com.ali.advancedtask.feature.signup.domain.repository

import com.ali.advancedtask.feature.signup.data.model.request.SignUpRequestDto
import com.ali.advancedtask.feature.signup.data.model.response.SignUpResponseDto

interface SignUpRepository {

    suspend fun registerUser(request: SignUpRequestDto): SignUpResponseDto

}