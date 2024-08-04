package com.ali.advancedtask.core.remote_services

import com.ali.advancedtask.feature.login.domin.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersApiService {
    @GET("users.json")
    suspend fun getUsers(): Map<String, User>

    @POST("users.json")
    suspend fun addUser(@Body user: User)
}