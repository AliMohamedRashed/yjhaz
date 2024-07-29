package com.ali.advancedtask.data

import com.ali.advancedtask.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersApiService {
    @GET("users.json")
    suspend fun getUsers(): Map<String,User>

    @POST("users.json")
    suspend fun addUser(@Body user: User)
}