package com.ali.advancedtask.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.data.UsersApiService
import com.ali.advancedtask.model.User
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersViewModel: ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val apiService: UsersApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl("https://yjhaz-495b5-default-rtdb.firebaseio.com/")
            .build()

        apiService = retrofit.create(UsersApiService::class.java)

    }

    fun fetchUsers() {
        viewModelScope.launch {
            apiService.getUsers().enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    response.body()?.let {
                        _users.postValue(it)
                    }

                }
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }
}