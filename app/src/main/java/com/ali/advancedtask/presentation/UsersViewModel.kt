package com.ali.advancedtask.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.YajhazApplication
import com.ali.advancedtask.data.local.UsersDataBase
import com.ali.advancedtask.data.remote.UsersApiService
import com.ali.advancedtask.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersViewModel: ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val apiService: UsersApiService

    private val usersDao = UsersDataBase.getDAOInstance(YajhazApplication.getApplicationContext())

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl("https://yjhaz-495b5-default-rtdb.firebaseio.com/")
            .build()

        apiService = retrofit.create(UsersApiService::class.java)

        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                apiService.getUsers().enqueue(object : Callback<Map<String, User>> {
                    override fun onResponse(call: Call<Map<String, User>>, response: Response<Map<String, User>>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                // Convert map to list
                                val userList = it.values.toList()
                                _users.postValue(userList)
                            } ?: run {
                                Log.e("UsersViewModel", "Response body is null")
                            }
                        } else {
                            Log.e("UsersViewModel", "Response not successful: ${response.errorBody()?.string()}")
                        }
                    }

                    override fun onFailure(call: Call<Map<String, User>>, t: Throwable) {
                        Log.e("UsersViewModel", "Failed to fetch users", t)
                    }
                })
            }
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                apiService.addUser(user).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            fetchUsers() // Refresh the user list after adding a new user
                        } else {
                            Log.e("UsersViewModel", "Failed to add user: ${response.errorBody()?.string()}")
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
            }
        }
    }

}