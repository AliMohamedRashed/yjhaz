package com.ali.advancedtask.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

val users = listOf(
    User(0,"Mohamed Ali","Cairo (15 Anasjhbajihfvbi street)","Mohamed@gmail.com","01145892876","Mohamed@2684"),
    User(1,"Ali Mohamed","Geddah (11 Anasjhbajihfvbi street)","Ali@gmail.com","01345333376","Ali9012"),
    User(2,"Samir Kamouna","Menofia(17 Anasjhbajihfvbi street)","Samir@gmail.com","01023692876","samirA12384")
)

@Parcelize
data class User (val id: Int, val name: String,val address:String, val email: String, val phoneNumber: String, val password: String) : Parcelable