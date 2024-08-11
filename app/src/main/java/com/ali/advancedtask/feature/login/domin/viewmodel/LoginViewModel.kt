
package com.ali.advancedtask.feature.login.domin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.core.storge_manager.StorageHandler
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
    private val storageHandler: StorageHandler
): ViewModel() {
    private var _state = MutableStateFlow(
        LoginScreenState(
            response = LoginResponseDto(
                null,"",0,false
            ),
            success = false,
            isLoading = true,
            error = null
        )
    )
    val state: StateFlow<LoginScreenState> = _state

    fun getUserLoggedIn(request: LoginRequestDto){
        viewModelScope.launch {
            try {
                val response = repository.loginUser(request)
                _state.value = _state.value.copy(
                    response= response,
                    success = response.success,
                    isLoading = false,
                    error = response.message
                )
                response.data?.token.let {token ->
                    if (token != null) {
                        storageHandler.setToken("user_token", token)
                    }
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}
