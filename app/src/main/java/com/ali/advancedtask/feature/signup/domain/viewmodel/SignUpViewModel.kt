package com.ali.advancedtask.feature.signup.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.core.State
import com.ali.advancedtask.feature.signup.data.model.request.SignUpRequestDto
import com.ali.advancedtask.feature.signup.data.model.response.SignUpResponseDto
import com.ali.advancedtask.feature.signup.domain.repository.SignUpRepository
import com.ali.advancedtask.feature.signup.domain.validation.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: SignUpRepository,
) : ViewModel() {

    private var _state = MutableStateFlow<State<SignUpResponseDto>?>(null)
    val state = _state.asStateFlow()

    fun registerNewUser(request: SignUpRequestDto) {
        viewModelScope.launch {
            repository.registerUser(request).collect { state ->
                _state.value = state
            }
        }
    }

    fun validateInputs(registerData: SignUpRequestDto, confirmPassword: String): ValidationResult {
        return when {
            registerData.name.isEmpty() -> ValidationResult.EMPTY_NAME
            registerData.name.length < 14 -> ValidationResult.NAME_TOO_SHORT
            !isEmailValid(registerData.email) -> ValidationResult.INVALID_EMAIL
            registerData.phone.isEmpty() -> ValidationResult.EMPTY_PHONE
            registerData.phone.length < 11 -> ValidationResult.PHONE_TOO_SHORT
            registerData.password.isEmpty() -> ValidationResult.EMPTY_PASSWORD
            registerData.password.length < 6 -> ValidationResult.PASSWORD_TOO_SHORT
            confirmPassword.isEmpty() -> ValidationResult.EMPTY_CONFIRM_PASSWORD
            registerData.password != confirmPassword -> ValidationResult.PASSWORD_MISMATCH
            else -> ValidationResult.SUCCESS
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}