package com.ali.advancedtask.data.repository.interfaces

import com.ali.advancedtask.domain.model.User

interface UsersRepository {

    suspend fun getAllUsers(): List<User>

    suspend fun addUser(user: User)
}