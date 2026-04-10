package com.example.api.di

import com.example.api.services.Authservice
import com.example.api.services.Uploadservice
import com.example.api.services.Userservice
import com.utils.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Retrofit Builder
    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
    }

    // OkHttp Client with Interceptor
    @Provides
    @Singleton
    fun provideHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    // Auth Service (Login / Register)
    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit.Builder): Authservice {
        return retrofit
            .build()
            .create(Authservice::class.java)
    }

    // Upload Service
    @Provides
    @Singleton
    fun providesUpload(retrofit: Retrofit.Builder): Uploadservice {
        return retrofit
            .build()
            .create(Uploadservice::class.java)
    }

    // User Service (Protected API)
    @Provides
    @Singleton
    fun provideUserService(
        retrofit: Retrofit.Builder,
        client: OkHttpClient
    ): Userservice {
        return retrofit
            .client(client)
            .build()
            .create(Userservice::class.java)
    }
}
