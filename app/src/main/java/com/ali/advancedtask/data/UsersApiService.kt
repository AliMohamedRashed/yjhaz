package com.ali.advancedtask.data

import com.ali.advancedtask.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersApiService {
    @GET("users.json")
    fun getUsers(): Call<List<User>>

    @POST("users.json")
    fun addUser(@Body user: User): Call<Void>
}