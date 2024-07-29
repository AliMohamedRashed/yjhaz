package com.ali.advancedtask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ali.advancedtask.model.User

@Dao
interface UsersDAO {
    @Query("Select * FROM users")
    suspend fun getAll(): List<LocalUser>

    @Insert
    suspend fun addUser(user: LocalUser)
}