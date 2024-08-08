    package com.ali.advancedtask.core.di

    import com.ali.advancedtask.core.remote_services.ClientRegisterApiService
    import com.ali.advancedtask.core.remote_services.LoginApiService
    import dagger.Module
    import dagger.Provides
    import dagger.hilt.InstallIn
    import dagger.hilt.components.SingletonComponent
    import retrofit2.Retrofit
    import retrofit2.converter.gson.GsonConverterFactory
    import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    object NetworkModule {
        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://yogahez.mountasher.online/")
                .addConverterFactory(GsonConverterFactory.create())
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

    }