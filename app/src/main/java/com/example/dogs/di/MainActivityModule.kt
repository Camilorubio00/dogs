package com.example.dogs.di

import com.example.dogs.core.CoroutinesDispatchers
import com.example.dogs.data.BASE_URL
import com.example.dogs.data.DogApi
import com.example.dogs.exceptions.ApiExceptionHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainActivityModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .readTimeout(20, SECONDS)
            .connectTimeout(20, SECONDS)
        return httpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient) : DogApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(DogApi::class.java)
    }

    @Singleton
    @Provides
    fun provideApiExceptionHandler() : ApiExceptionHandler {
        return ApiExceptionHandler()
    }

    @Provides
    @Singleton
    fun provideCoroutinesDispatchers() = CoroutinesDispatchers()
}
