package com.ali.advancedtask.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.model.User
import com.ali.advancedtask.model.UsersRepository
import kotlinx.coroutines.launch

class UsersViewModel: ViewModel() {
    private val _users = MutableLiveData(emptyList<User>())
    val users: LiveData<List<User>> get() = _users

    private val repo = UsersRepository()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            val usersList = repo.getAllUsers()
            _users.postValue(usersList)
        }
    }

    fun addUser(user: User){
        viewModelScope.launch {
            repo.addUser(user)
            fetchUsers()
        }
    }
}