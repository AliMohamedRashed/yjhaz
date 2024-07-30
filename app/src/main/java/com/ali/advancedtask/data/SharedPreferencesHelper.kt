package com.ali.advancedtask.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object SharedPreferencesHelper {
    private const val PREFS_NAME = "my_prefs"
    private const val TOKEN_KEY = "access_token"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, token: String) {
        val prefs = getPreferences(context)
        val editor = prefs.edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
        Log.d("SharedPreferencesHelper", "Token saved: $token")
    }

    fun getToken(context: Context): String? {
        val prefs = getPreferences(context)
        return prefs.getString(TOKEN_KEY, null)
    }

    fun removeToken(context: Context) {
        val prefs = getPreferences(context)
        val editor = prefs.edit()
        editor.remove(TOKEN_KEY)
        editor.apply()
    }
}