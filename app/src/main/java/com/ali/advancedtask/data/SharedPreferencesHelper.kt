package com.ali.advancedtask.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object SharedPreferencesHelper {
    private const val PREFS_NAME = "user_prefs"
    private const val USER_ID_KEY = "user_id"
    private const val USER_NAME_KEY = "user_name"
    private const val CHECKBOX_STATE_KEY = "checkbox_stat"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    fun saveUserId(context: Context, userId: String) {
        val prefs = getPreferences(context)
        val editor = prefs.edit()
        editor.putString(USER_ID_KEY, userId)
        editor.apply()
        Log.d("SharedPreferencesHelper", "User ID saved: $userId")
    }

    fun getUserId(context: Context): String? {
        val prefs = getPreferences(context)
        return prefs.getString(USER_ID_KEY, null)
    }

    fun removeUserId(context: Context) {
        val prefs = getPreferences(context)
        val editor = prefs.edit()
        editor.remove(USER_ID_KEY)
        editor.apply()
    }

    fun saveUserName(context: Context, userName: String) {
        val prefs = getPreferences(context)
        val editor = prefs.edit()
        editor.putString(USER_NAME_KEY, userName)
        editor.apply()
        Log.d("SharedPreferencesHelper", "UserName saved: $userName")
    }

    fun getUserName(context: Context): String? {
        val prefs = getPreferences(context)
        return prefs.getString(USER_NAME_KEY, null)
    }

    fun removeUserName(context: Context) {
        val prefs = getPreferences(context)
        val editor = prefs.edit()
        editor.remove(USER_NAME_KEY)
        editor.apply()
    }

    fun saveCheckBoxState(context: Context, isChecked: Boolean) {
        val prefs = getPreferences(context)
        val editor = prefs.edit()
        editor.putBoolean(CHECKBOX_STATE_KEY, isChecked)
        editor.apply()
        Log.d("SharedPreferencesHelper", "CheckBox state saved: $isChecked")
    }

    fun getCheckBoxState(context: Context): Boolean {
        val prefs = getPreferences(context)
        return prefs.getBoolean(CHECKBOX_STATE_KEY, false)
    }

    fun removeCheckBoxState(context: Context) {
        val prefs = getPreferences(context)
        val editor = prefs.edit()
        editor.remove(CHECKBOX_STATE_KEY)
        editor.apply()
    }
}