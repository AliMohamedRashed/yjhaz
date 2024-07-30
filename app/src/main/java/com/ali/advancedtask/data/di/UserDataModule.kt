package com.ali.advancedtask.data.di

import com.ali.advancedtask.data.CategoriesRepository
import com.ali.advancedtask.data.PopularRepository
import com.ali.advancedtask.data.TrendingRepository
import com.ali.advancedtask.data.UsersRepository
import com.ali.advancedtask.data.remote.CategoriesApiService
import com.ali.advancedtask.data.remote.PopularApiService
import com.ali.advancedtask.data.remote.TrendingApiService
import com.ali.advancedtask.data.remote.UsersApiService
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
object UserDataModule {
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
            .baseUrl("https://yjhaz-495b5-default-rtdb.firebaseio.com/")
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
        return TrendingRepository(apiService)
    }

    @Provides
    @Singleton
    fun providePopularApiService(retrofit: Retrofit): PopularApiService {
        return retrofit.create(PopularApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePopularRepository(apiService: PopularApiService): PopularRepository {
        return PopularRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideCategoriesApiService(retrofit: Retrofit): CategoriesApiService {
        return retrofit.create(CategoriesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoriesRepository(apiService: CategoriesApiService): CategoriesRepository {
        return CategoriesRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideUsersApiService(retrofit: Retrofit): UsersApiService {
        return retrofit.create(UsersApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUsersRepository(apiService: UsersApiService): UsersRepository {
        return UsersRepository(apiService)
    }


}