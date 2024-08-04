package com.ali.advancedtask.feature.login.di

import com.ali.advancedtask.core.remote_services.UsersApiService
import com.ali.advancedtask.feature.login.data.repository.UsersRepositoryImpl
import com.ali.advancedtask.feature.login.domin.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object LoginModule {

    @Provides
    fun provideUsersRepository(apiService: UsersApiService): UsersRepository {
        return UsersRepositoryImpl(apiService)
    }

}