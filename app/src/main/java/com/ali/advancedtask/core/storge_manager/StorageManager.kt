package com.ali.advancedtask.core.storge_manager

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


private const val PREFS_NAME = "YAJHAZ_APP"

class StorageManager @Inject constructor(@ApplicationContext private val context: Context) :
    StorageHandler {

    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    override fun setToken(key: String, value: String) =
        sharedPreferences.edit().putString(key, value).apply()

    override fun setString(key: String, value: String) =
        sharedPreferences.edit().putString(key, value).apply()

    override fun getString(key: String) = sharedPreferences.getString(key, null)


    override fun getToken(key: String) = sharedPreferences.getString(key, null)

    override fun removeAll(sharedPrefName: String) = sharedPreferences.edit().clear().apply()



}