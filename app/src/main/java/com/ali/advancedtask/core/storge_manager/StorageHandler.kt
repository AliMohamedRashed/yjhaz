package com.ali.advancedtask.core.storge_manager

interface StorageHandler{

    fun setToken(key: String, value: String)
    fun setString(key: String, value: String)
    fun getString(key: String) :String?

    fun getToken(key: String) :String?

    fun removeAll(sharedPrefName: String)

}