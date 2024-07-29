package com.ali.advancedtask.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class LocalUser (
    @ColumnInfo(name = "gym_id")
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    @ColumnInfo(name = "user_name")
    val name: String,
    @ColumnInfo(name = "user_email")
    val email: String,
    @ColumnInfo(name = "user_phone_number")
    val phoneNumber: String,
    @ColumnInfo(name = "user_password")
    val password: String
) : Parcelable