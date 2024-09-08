package com.ali.advancedtask.feature.ad.di

import com.ali.advancedtask.feature.ad.data.repository.AdRepositoryImpl
import com.ali.advancedtask.feature.ad.domain.repository.AdRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object AdModule {
    @Provides
    fun provideAdRepository(): AdRepository {
        return AdRepositoryImpl()
    }
}