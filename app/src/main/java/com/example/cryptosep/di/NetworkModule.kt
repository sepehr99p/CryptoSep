package com.example.cryptosep.di

import android.content.Context
import com.example.cryptosep.data.remote.KucoinApiService
import com.example.cryptosep.data.utils.NetworkConnection
import com.example.cryptosep.domain.utils.BASE_URL
import com.example.cryptosep.domain.utils.TIME_OUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkConnection(
        @ApplicationContext context: Context
    ): NetworkConnection {
        return NetworkConnection(context)
    }

    @Provides
    @Singleton
    fun provideKucoinApiService(
        retrofit: Retrofit
    ): KucoinApiService {
        return retrofit.create(KucoinApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        converterFactory: GsonConverterFactory,
        httpClient: OkHttpClient.Builder
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .client(httpClient.build())
            .build()
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS) // Adjust as needed
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)    // Adjust as needed
            .retryOnConnectionFailure(true)
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

}