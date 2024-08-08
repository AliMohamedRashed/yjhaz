package com.ali.advancedtask.feature.signup.domain

import com.ali.advancedtask.feature.signup.data.model.response.SignUpResponseDto


data class SignUpScreenState(
    var response: SignUpResponseDto,
    var isLoading: Boolean,
    var error: String? = null
)
