package com.ali.advancedtask.feature.login.domin.repository

import com.ali.advancedtask.feature.login.domin.model.User

interface UsersRepository {

    suspend fun getAllUsers(): List<User>

    suspend fun addUser(user: User)
}