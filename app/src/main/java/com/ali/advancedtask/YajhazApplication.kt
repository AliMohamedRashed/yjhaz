package com.ali.advancedtask

import android.app.Application
import android.content.Context

class YajhazApplication: Application() {

    init {
        application = this
    }
    companion object{
        private  lateinit var application: Application
        fun getApplicationContext(): Context = application.applicationContext
    }
}