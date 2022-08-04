package com.soul.suhrob.soul.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.soul.suhrob.soul.app.BaseApplication
import com.soul.suhrob.soul.di.modue.ActivityBuildersModule
import com.soul.suhrob.soul.di.modue.AppModule
import com.soul.suhrob.soul.di.modue.ViewModelFactoryModule
import javax.inject.Singleton

/**
 * Created by Microstar on 19.08.2021
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class,
        ViewModelFactoryModule::class,
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}
