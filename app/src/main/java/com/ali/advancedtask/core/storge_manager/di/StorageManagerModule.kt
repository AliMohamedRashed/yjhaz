package com.ali.advancedtask.core.storge_manager.di

import android.content.Context
import com.ali.advancedtask.core.storge_manager.StorageHandler
import com.ali.advancedtask.core.storge_manager.StorageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageManagerModule {
    @Provides
    @Singleton
    fun provideStorageManager(@ApplicationContext context: Context): StorageHandler =
        StorageManager(context)
}