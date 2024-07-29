package com.ali.advancedtask.model

import android.util.Log
import com.ali.advancedtask.data.UsersApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersRepository {

    private val apiService: UsersApiService = Retrofit.Builder()
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .baseUrl("https://yjhaz-495b5-default-rtdb.firebaseio.com/")
        .build()
        .create(UsersApiService::class.java)

    suspend fun getAllUsers() = withContext(Dispatchers.IO) {
        return@withContext apiService.getUsers().values.toList<User>()
    }
    suspend fun addUser(user: User) {
        try {
            withContext(Dispatchers.IO) {
                apiService.addUser(user)
            }
        } catch (e: Exception) {
            // Handle the exception, e.g., log it or update UI with an error message
            Log.e("UsersViewModel", "Error adding user", e)
        }
    }
}