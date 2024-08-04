package com.ali.advancedtask.core.storge_manager

interface StorageHandler {

    fun setString(key: String, value: String)
    fun getString(key: String) :String?

    fun setInt(key: String, value: Int)
    fun getInt(key: String) : Int?

    fun setBoolean(key: String, value: Boolean)
    fun getBoolean(key: String) : Boolean?

    fun removeByKey(key: String)

    fun removeAll(sharedPrefName: String)
}