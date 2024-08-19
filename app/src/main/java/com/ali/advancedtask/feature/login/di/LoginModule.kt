package com.ali.advancedtask.feature.login.di

import com.ali.advancedtask.core.remote_services.LoginApiService
import com.ali.advancedtask.core.user_manager.UserHandler
import com.ali.advancedtask.feature.login.data.repository.LoginRepositoryImpl
import com.ali.advancedtask.feature.login.domin.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent



@Module
@InstallIn(ViewModelComponent::class)
object LoginModule {


    @Provides
    fun provideLoginRepository(apiService: LoginApiService,userHandler: UserHandler): LoginRepository {
        return LoginRepositoryImpl(apiService,userHandler)
    }

}