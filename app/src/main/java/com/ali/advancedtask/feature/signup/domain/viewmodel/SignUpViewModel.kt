package com.ali.advancedtask.feature.signup.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.core.State
import com.ali.advancedtask.core.user_manager.UserHandler
import com.ali.advancedtask.feature.activities.MainActivity
import com.ali.advancedtask.feature.signup.data.model.request.SignUpRequestDto
import com.ali.advancedtask.feature.signup.data.model.response.SignUpResponseDto
import com.ali.advancedtask.feature.signup.domain.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: SignUpRepository,
    private val userHandler: UserHandler,
): ViewModel() {

    private var _state = MutableStateFlow<State<SignUpResponseDto>?>(null)
    val state = _state.asStateFlow()

    fun registerNewUser(request: SignUpRequestDto, confirmPassword: String) {
        viewModelScope.launch {
            if (request.name.isNotEmpty() && request.email.isNotEmpty() && request.phone.isNotEmpty() &&
                request.password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (request.password == confirmPassword) {
                    repository.registerUser(request).collect{ state ->
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
                    MainActivity.showToast("The entered password must be the same!")
                }
            } else {
                MainActivity.showToast("All fields must be filled!")
            }
        }
    }

}