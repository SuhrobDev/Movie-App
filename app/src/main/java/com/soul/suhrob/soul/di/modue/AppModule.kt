package com.soul.suhrob.soul.di.modue

import android.app.Application
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import com.soul.suhrob.soul.utils.NetworkConnectionListener
import com.soul.suhrob.soul.utils.SharedPreferencesHelper
import javax.inject.Singleton

/**
 * Created by Microstar on 19.08.2021
 */

@Module(
    includes = [
        NetworkModule::class,
        RoomModule::class
    ]
)
object AppModule {

    @Singleton
    @Provides
    fun provideShared(application: Application) = SharedPreferencesHelper(application.applicationContext)

    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideNetworkListener(application: Application) = NetworkConnectionListener(application)

}