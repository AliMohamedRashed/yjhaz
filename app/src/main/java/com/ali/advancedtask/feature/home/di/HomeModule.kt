package com.ali.advancedtask.feature.home.di

import com.ali.advancedtask.core.remote_services.BaseCategoriesApiService
import com.ali.advancedtask.core.remote_services.PopularSellersApiService
import com.ali.advancedtask.core.remote_services.TrendingSellersApiService
import com.ali.advancedtask.feature.home.data.repository.HomeRepositoryImpl
import com.ali.advancedtask.feature.home.domin.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    fun provideHomeRepository(
        baseCategoriesApiService: BaseCategoriesApiService,
        popularSellersApiService: PopularSellersApiService,
        trendingSellersApiService: TrendingSellersApiService
    ): HomeRepository {
        return HomeRepositoryImpl(baseCategoriesApiService,popularSellersApiService,trendingSellersApiService)
    }

}