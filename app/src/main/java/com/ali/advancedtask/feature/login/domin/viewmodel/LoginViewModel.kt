package com.ali.advancedtask.feature.login.domin.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.feature.login.data.model.request.LoginRequestDto
import com.ali.advancedtask.feature.login.data.model.response.LoginResponseDto
import com.ali.advancedtask.feature.login.domin.LoginScreenState
import com.ali.advancedtask.feature.login.domin.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
): ViewModel() {
    private var _state by mutableStateOf(
        LoginScreenState(
            response = LoginResponseDto(
                null,"",0,false
            ),
        )
    )
    val state: State<LoginScreenState>
        get() = derivedStateOf { _state }

    fun getUserLoggedIn(request: LoginRequestDto){
        viewModelScope.launch {
            val response = repository.loginUser(request)
            _state = _state.copy(
                response= response
            )
        }
    }
}