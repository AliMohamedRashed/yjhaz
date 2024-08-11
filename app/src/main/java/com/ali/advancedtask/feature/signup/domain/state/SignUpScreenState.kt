package com.ali.advancedtask.feature.signup.domain.state

import com.ali.advancedtask.feature.signup.data.model.response.SignUpResponseDto


data class SignUpScreenState(
    var response: SignUpResponseDto,
    var success: Boolean,
    var isLoading: Boolean,
    var error: String? = null
)
