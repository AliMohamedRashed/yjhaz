package com.ali.advancedtask.domain.viewmodel.user_viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.data.repository.interfaces.UsersRepository
import com.ali.advancedtask.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UsersRepository,
): ViewModel() {

    private val _users = MutableLiveData(emptyList<User>())

    val users: LiveData<List<User>> get() = _users
    init {
        fetchUsers()
    }
    private fun fetchUsers() {
        viewModelScope.launch {
            val usersList = repository.getAllUsers()
            _users.postValue(usersList)
        }
    }

    fun getUser(userEmail: String, password: String): User? {
        _users.value?.forEach{
            if (it.email == userEmail && it.password == password) {
                return it
            }
        }
        return null
    }

    fun addUser(user: User){
        viewModelScope.launch {
            repository.addUser(user)
        }
    }
}