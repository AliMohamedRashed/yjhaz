package com.ali.advancedtask.core.storge_manager

interface StorageHandler{

    fun setToken(key: String, value: String)

    fun getToken(key: String) :String?

    fun removeToken(key: String)

}