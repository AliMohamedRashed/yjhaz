package com.ali.advancedtask.data.remote

import com.ali.advancedtask.domain.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersApiService {
    @GET("users.json")
    suspend fun getUsers(): Map<String, User>

    @POST("users.json")
    suspend fun addUser(@Body user: User)
}