package com.soul.suhrob.soul.di.modue

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.soul.suhrob.soul.fragments.ActorDetailFragment
import com.soul.suhrob.soul.fragments.MovieDetailFragment
import com.soul.suhrob.soul.fragments.SplashFragment
import com.soul.suhrob.soul.fragments.main.MainFragment

/**
 * Created by Microstar on 19.08.2021
 */
@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun homeFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun actorFragment(): ActorDetailFragment

    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun movieDetailFragment(): MovieDetailFragment
}