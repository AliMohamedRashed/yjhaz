package com.ali.advancedtask.core.user_manager.di

import com.ali.advancedtask.core.storge_manager.StorageHandler
import com.ali.advancedtask.core.user_manager.UserHandler
import com.ali.advancedtask.core.user_manager.UserManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    fun provideUserManager(storageHandler: StorageHandler): UserHandler =
        UserManager(storageHandler)
}