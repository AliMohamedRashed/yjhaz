package com.ali.advancedtask.feature.login.domin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.core.State
import com.ali.advancedtask.feature.login.data.model.request.LoginRequestDto
import com.ali.advancedtask.feature.login.data.model.response.LoginResponseDto
import com.ali.advancedtask.feature.login.domin.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
) : ViewModel() {
    private var _state = MutableStateFlow<State<LoginResponseDto>?>(null)
    val state = _state.asStateFlow()

    fun getUserLoggedIn(request: LoginRequestDto) {
        viewModelScope.launch {
            _state.value = State.Loading
            repository.loginUser(request).collect { state ->
                _state.value = state
            }
        }
    }

}
