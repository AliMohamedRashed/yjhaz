package com.ali.advancedtask.data

import com.ali.advancedtask.data.remote.UsersApiService
import com.ali.advancedtask.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val apiService: UsersApiService
){
    private var highestId = 9
    suspend fun getAllUsers() = withContext(Dispatchers.IO) {
        val response = apiService.getUsers()
        return@withContext response.values.toList<User>()
    }
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