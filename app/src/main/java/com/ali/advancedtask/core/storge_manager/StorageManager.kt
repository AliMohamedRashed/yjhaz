package com.ali.advancedtask.core.storge_manager

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


private const val PREFS_NAME = "YAJHAZ_APP"

class StorageManager @Inject constructor(@ApplicationContext private val context: Context) :
    StorageHandler {


    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


    override fun setString(key: String, value: String) =
        sharedPreferences.edit().putString(key, value).apply()

    override fun getString(key: String) = sharedPreferences.getString(key, null)


    override fun setInt(key: String, value: Int) =
        sharedPreferences.edit().putInt(key, value).apply()

    override fun getInt(key: String) = sharedPreferences.getInt(key, 0)


    override fun setBoolean(key: String, value: Boolean) =
        sharedPreferences.edit().putBoolean(key, value).apply()

    override fun getBoolean(key: String) = sharedPreferences.getBoolean(key, false)


    override fun removeByKey(key: String) = sharedPreferences.edit().remove(key).apply()

    override fun removeAll(sharedPrefName: String) = sharedPreferences.edit().clear().apply()


}