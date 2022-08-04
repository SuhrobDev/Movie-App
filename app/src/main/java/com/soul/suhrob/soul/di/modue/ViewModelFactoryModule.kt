package com.soul.suhrob.soul.di.modue

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import com.soul.suhrob.soul.viewmodels.DaggerViewModelFactory

/**
 * Created by Microstar on 19.08.2021
 */
@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactor(modelProviderFactory: DaggerViewModelFactory?): ViewModelProvider.Factory?
}