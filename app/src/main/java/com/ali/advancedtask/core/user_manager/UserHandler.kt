package com.ali.advancedtask.core.user_manager

interface UserHandler {

    fun setUserToken(userId: String)
    fun getUserToken(): String?
    fun removeUserToken()

    fun setUserName( userName: String)
    fun getUserName(): String?
    fun removeUserName()

}