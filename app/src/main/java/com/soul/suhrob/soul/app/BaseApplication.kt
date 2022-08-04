package com.soul.suhrob.soul.app

import dagger.android.support.DaggerApplication
import com.soul.suhrob.soul.di.component.DaggerAppComponent


/**
 * Created by Microstar on 19.08.2021
 */
class BaseApplication : DaggerApplication() {
    override fun applicationInjector() = DaggerAppComponent.builder().application(this).build()
}