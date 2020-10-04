package com.example.todo.di.modules.api

import com.example.todo.BuildConfig
import com.example.todo.api.ApiService
import com.example.todo.network.adapter.LiveDataCallAdapterFactory
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@Suppress("unused")
class ApiModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): HttpUrl {
        return API_URL
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val mBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) mBuilder.addNetworkInterceptor(StethoInterceptor())
        return mBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(mBaseUrl: HttpUrl, mClient: OkHttpClient, mMoshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(mClient)
            .baseUrl(mBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(mMoshi))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(mRetrofit: Retrofit): ApiService {
        return mRetrofit.create(ApiService::class.java)
    }

    companion object {
        val API_URL: HttpUrl = HttpUrl.get("https://jsonplaceholder.typicode.com/")
    }
}
