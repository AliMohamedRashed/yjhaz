package com.ali.advancedtask.feature.login.domin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.core.State
import com.ali.advancedtask.core.user_manager.UserHandler
import com.ali.advancedtask.feature.activities.MainActivity
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
    private val userHandler: UserHandler,
) : ViewModel() {
    private var _state = MutableStateFlow<State<LoginResponseDto>?>(null)
    val state = _state.asStateFlow()

    fun getUserLoggedIn(request: LoginRequestDto) {
        viewModelScope.launch {
            if (request.email.isNotEmpty() && request.password.isNotEmpty()) {
                _state.value = State.Loading
                repository.loginUser(request).collect { state ->
                    _state.value = state
                    if(state is State.Success ) {
                        if(state.data.success){
                            userHandler.run {
                                setUserToken(state.data.data!!.token)
                                setUserName(state.data.data.name)
                            }
                        }else{
                            MainActivity.showToast(state.data.message )
                        }
                    }
                }
            } else {
                _state.value = State.Error(Exception("All fields must be filled!"))
            }
        }
    }

}
