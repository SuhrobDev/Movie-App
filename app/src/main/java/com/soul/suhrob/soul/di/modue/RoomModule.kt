package com.soul.suhrob.soul.di.modue

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import com.soul.suhrob.soul.room.AppDatabase
import com.soul.suhrob.soul.utils.Constants
import javax.inject.Singleton

/**
 * Created by Microstar on 19.08.2021
 */
@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesAppDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext, AppDatabase::class.java, Constants.DATABASE_NAME
    )
//        .allowMainThreadQueries()
//        .fallbackToDestructiveMigration()
//        .addMigrations(Migrations.isBonusTypeMigration)
        .build()

    @Singleton
    @Provides
    fun providesCardDao(appDatabase: AppDatabase) = appDatabase.getCardDao()

}