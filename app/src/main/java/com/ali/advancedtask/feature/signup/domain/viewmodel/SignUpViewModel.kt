package com.ali.advancedtask.feature.signup.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.core.user_manager.UserHandler
import com.ali.advancedtask.feature.activities.MainActivity
import com.ali.advancedtask.feature.signup.domain.state.SignUpScreenState
import com.ali.advancedtask.feature.signup.data.model.request.SignUpRequestDto
import com.ali.advancedtask.feature.signup.data.model.response.SignUpResponseDto
import com.ali.advancedtask.feature.signup.domain.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: SignUpRepository,
    private val userHandler: UserHandler,
): ViewModel() {

    private var _state = MutableStateFlow(
        SignUpScreenState(
            response = SignUpResponseDto(
                null,"",0,false
            ),
            success = false,
            isLoading = true,
            error = null
        )
    )
    val state: StateFlow<SignUpScreenState> = _state

    fun registerNewUser(request: SignUpRequestDto) {
        viewModelScope.launch {
            if (request.name.isNotEmpty() && request.email.isNotEmpty() && request.phone.isNotEmpty() && request.password.isNotEmpty()) {
                val response = repository.registerUser(request)
                _state.value = _state.value.copy(
                    response = response,
                    success = response.success,
                    isLoading = false,
                    error = response.message
                )
                response.data?.let {data->
                    userHandler.setUserToken(data.token)
                    userHandler.setUserName(data.name)
                }
            } else {
                MainActivity.showToast("All fields must be filled!")
            }
        }
    }

}