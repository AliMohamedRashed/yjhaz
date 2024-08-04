package com.ali.advancedtask.core.di

import com.ali.advancedtask.feature.home.data.repository.CategoriesRepositoryImpl
import com.ali.advancedtask.feature.home.data.repository.PopularRepositoryImpl
import com.ali.advancedtask.feature.home.data.repository.TrendingRepositoryImpl
import com.ali.advancedtask.feature.login.data.repository.UsersRepositoryImpl
import com.ali.advancedtask.core.remote_services.CategoriesApiService
import com.ali.advancedtask.core.remote_services.PopularApiService
import com.ali.advancedtask.core.remote_services.TrendingApiService
import com.ali.advancedtask.core.remote_services.UsersApiService
import com.ali.advancedtask.feature.home.domin.repository.CategoriesRepository
import com.ali.advancedtask.feature.home.domin.repository.PopularRepository
import com.ali.advancedtask.feature.home.domin.repository.TrendingRepository
import com.ali.advancedtask.feature.login.domin.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val interceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build()

//            val token = SharedPreferencesHelper.getToken(context)
//            if (token != null) {
//                requestBuilder.addHeader("Authorization", "Bearer $token")
//            }

           return@Interceptor chain.proceed(requestBuilder)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://users-bfc61-default-rtdb.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideTrendingApiService(retrofit: Retrofit): TrendingApiService {
        return retrofit.create(TrendingApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTrendingRepository(apiService: TrendingApiService): TrendingRepository {
        return TrendingRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providePopularApiService(retrofit: Retrofit): PopularApiService {
        return retrofit.create(PopularApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePopularRepository(apiService: PopularApiService): PopularRepository {
        return PopularRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCategoriesApiService(retrofit: Retrofit): CategoriesApiService {
        return retrofit.create(CategoriesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoriesRepository(apiService: CategoriesApiService): CategoriesRepository {
        return CategoriesRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUsersApiService(retrofit: Retrofit): UsersApiService {
        return retrofit.create(UsersApiService::class.java)
    }
}