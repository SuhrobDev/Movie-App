package com.soul.suhrob.soul.di.modue

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.soul.suhrob.soul.MainActivity

/**
 * Created by Microstar on 19.08.2021
 */

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class])
    abstract fun contributeMainActivity(): MainActivity
}