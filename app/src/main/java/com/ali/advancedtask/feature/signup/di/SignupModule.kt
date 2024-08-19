package com.ali.advancedtask.feature.signup.di

import com.ali.advancedtask.core.remote_services.ClientRegisterApiService
import com.ali.advancedtask.feature.signup.data.repository.SignUpRepositoryImpl
import com.ali.advancedtask.feature.signup.domain.repository.SignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object SignupModule {

    @Provides
    fun provideSignUpRepository(apiService: ClientRegisterApiService): SignUpRepository {
        return SignUpRepositoryImpl(apiService)
    }

}