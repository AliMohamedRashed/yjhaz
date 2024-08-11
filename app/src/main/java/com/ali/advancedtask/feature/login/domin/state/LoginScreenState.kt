package com.ali.advancedtask.feature.login.domin.state

import com.ali.advancedtask.feature.login.data.model.response.LoginResponseDto

data class LoginScreenState(
    var response: LoginResponseDto,
    var success: Boolean,
    var isLoading: Boolean,
    var error: String? = null
)