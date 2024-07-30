package com.ali.advancedtask.data

import android.util.Log
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
        return@withContext apiService.getUsers().values.toList<User>()
    }
    suspend fun addUser(user: User) {
        try {
            withContext(Dispatchers.IO) {
                highestId += 1
                user.id = highestId
                apiService.addUser(user)
            }
        } catch (e: Exception) {
            // Handle the exception, e.g., log it or update UI with an error message
            Log.e("UsersViewModel", "Error adding user", e)
        }
    }
}