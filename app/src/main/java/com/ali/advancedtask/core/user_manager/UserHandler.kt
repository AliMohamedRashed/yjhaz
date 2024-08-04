package com.ali.advancedtask.core.user_manager

import android.content.Context
import android.util.Log

interface UserHandler {

    fun setUserId( userId: String)
    fun getUserId(): String?
    fun removeUserId()

    fun setUserName( userName: String)
    fun getUserName(): String?
    fun removeUserName()

    fun setCheckBoxState(isChecked: Boolean)
    fun getCheckBoxState(): Boolean?
    fun removeCheckBoxState()
}