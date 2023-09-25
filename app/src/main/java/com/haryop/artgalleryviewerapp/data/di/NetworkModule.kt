package com.haryop.artgalleryviewerapp.data.di

import com.haryop.artgalleryviewerapp.data.remote.ApiServices
import com.haryop.artgalleryviewerapp.data.helper.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl()=Constants.BASE_URL

    fun logOkHttplient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return client
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(baseUrl: String): ApiServices =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(logOkHttplient())
            .build()
            .create(ApiServices::class.java)
}