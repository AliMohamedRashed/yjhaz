package com.ali.advancedtask.feature.signup.data.model.request

data class SignUpRequestDto(
    val name: String,
    val email: String,
    val phone: String,
    val password: String
)
