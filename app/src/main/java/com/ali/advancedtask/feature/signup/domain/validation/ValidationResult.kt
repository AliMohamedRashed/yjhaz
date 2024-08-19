package com.ali.advancedtask.feature.signup.domain.validation

enum class ValidationResult {
    SUCCESS,
    EMPTY_NAME,
    NAME_TOO_SHORT,
    INVALID_EMAIL,
    EMPTY_PHONE,
    PHONE_TOO_SHORT,
    EMPTY_PASSWORD,
    PASSWORD_TOO_SHORT,
    EMPTY_CONFIRM_PASSWORD,
    PASSWORD_MISMATCH
}