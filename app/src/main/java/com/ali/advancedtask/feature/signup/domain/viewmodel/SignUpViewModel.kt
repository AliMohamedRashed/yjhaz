package com.ali.advancedtask.feature.signup.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.core.storge_manager.StorageHandler
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
    private val storageHandler: StorageHandler
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
            try {
                val response = repository.registerUser(request)
                _state.value = _state.value.copy(
                    response = response,
                    success = response.success,
                    isLoading = false,
                    error = response.message
                )
                response.data?.token.let { token ->
                    if (token != null) {
                        storageHandler.setString("user_token", token)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}