package com.ali.advancedtask.data


val users = listOf(
    User(0,"Mohamed Ali","Mohamed@gmail.com","01145892876","Mohamed@2684"),
    User(1,"Ali Mohamed","Ali@gmail.com","01345333376","Ali9012"),
    User(2,"Samir Kamouna","Samir@gmail.com","01023692876","samirA12384")
)

data class User (val id: Int, val name: String, val email: String, val phoneNumber: String, val password: String)