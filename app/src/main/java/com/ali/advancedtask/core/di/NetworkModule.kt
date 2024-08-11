    package com.ali.advancedtask.core.di

    import com.ali.advancedtask.core.remote_services.BaseCategoriesApiService
    import com.ali.advancedtask.core.remote_services.ClientRegisterApiService
    import com.ali.advancedtask.core.remote_services.LoginApiService
    import com.ali.advancedtask.core.remote_services.PopularSellersApiService
    import com.ali.advancedtask.core.remote_services.TrendingSellersApiService
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
                .baseUrl("https://yogahez.mountasher.online/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

        @Provides
        fun provideClientRegisterApiService(retrofit: Retrofit): ClientRegisterApiService{
            return retrofit.create(ClientRegisterApiService::class.java)
        }

        @Provides
        fun provideLoginApiService(retrofit: Retrofit): LoginApiService{
            return retrofit.create(LoginApiService::class.java)
        }

        @Provides
        fun provideBaseCategoriesApiService(retrofit: Retrofit): BaseCategoriesApiService{
            return retrofit.create(BaseCategoriesApiService::class.java)
        }

        @Provides
        fun providePopularSellersApiService(retrofit: Retrofit): PopularSellersApiService {
            return retrofit.create(PopularSellersApiService::class.java)
        }

        @Provides
        fun provideTrendingSellersApiService(retrofit: Retrofit): TrendingSellersApiService {
            return retrofit.create(TrendingSellersApiService::class.java)
        }

    }