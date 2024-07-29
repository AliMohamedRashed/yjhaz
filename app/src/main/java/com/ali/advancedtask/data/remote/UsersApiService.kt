package com.ali.advancedtask.data.remote

import com.ali.advancedtask.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersApiService {
    @GET("users.json")
    fun getUsers(): Call<Map<String, User>>

    @POST("users.json")
    fun addUser(@Body user: User): Call<Void>
}