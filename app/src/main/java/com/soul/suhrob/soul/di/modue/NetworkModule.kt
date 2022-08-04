package com.soul.suhrob.soul.di.modue

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.soul.suhrob.soul.network.services.MovieService
import com.soul.suhrob.soul.utils.Constants
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by Microstar on 19.08.2021
 */

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().setLenient().create()
                )
            ).build()
    }

    @Singleton
    @Provides
    fun provideLogin(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES)
            .callTimeout(1, TimeUnit.MINUTES).addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder()
                chain.proceed(newRequest.build())
            }.addInterceptor(chuckerInterceptor).addInterceptor(httpLoggingInterceptor).build()
    }


    @Provides
    @Singleton
    fun provideChucker(application: Application): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(application.applicationContext).maxContentLength(250_000L)
            .alwaysReadResponseBody(true).build()
    }

}