package com.ali.advancedtask.feature.login.domin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.core.user_manager.UserHandler
import com.ali.advancedtask.feature.activities.MainActivity
import com.ali.advancedtask.feature.login.data.model.request.LoginRequestDto
import com.ali.advancedtask.feature.login.data.model.response.LoginResponseDto
import com.ali.advancedtask.feature.login.domin.state.LoginScreenState
import com.ali.advancedtask.feature.login.domin.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val userHandler: UserHandler,
) : ViewModel() {
    private var _state = MutableStateFlow(
        LoginScreenState(
            response = LoginResponseDto(
                null, "", 0, false
            ),
            success = false,
            isLoading = false,
            error = null
        )
    )
    val state: StateFlow<LoginScreenState> = _state

    fun getUserLoggedIn(request: LoginRequestDto) {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            if (request.email.isNotEmpty() && request.password.isNotEmpty()) {
                val response = repository.loginUser(request)
                _state.value = _state.value.copy(
                    response = response,
                    success = response.success,
                    isLoading = false,
                    error = response.message
                )
                response.data?.let { data ->
                    data.token?.let { token ->
                        userHandler.setUserToken(token)
                    }
                    data.name?.let { name ->
                        userHandler.setUserName(name)
                    }
                }
            } else {
                _state.value = _state.value.copy(isLoading = false)
                MainActivity.showToast("All fields must be filled!")
            }
        }
    }
}
