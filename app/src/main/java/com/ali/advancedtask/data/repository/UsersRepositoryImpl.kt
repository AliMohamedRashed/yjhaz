package com.ali.advancedtask.data.repository

import com.ali.advancedtask.data.remote.UsersApiService
import com.ali.advancedtask.data.repository.interfaces.UsersRepository
import com.ali.advancedtask.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val apiService: UsersApiService
) : UsersRepository {
    private var highestId = 9
    override
    suspend fun getAllUsers() = withContext(Dispatchers.IO) {
        val response = apiService.getUsers()
        response.values.toList()
    }

    override
    suspend fun addUser(user: User) {
        try {
            withContext(Dispatchers.IO) {
                highestId += 1
                user.id = highestId
                apiService.addUser(user)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}