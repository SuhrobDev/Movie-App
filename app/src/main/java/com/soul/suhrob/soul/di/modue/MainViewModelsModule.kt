package com.soul.suhrob.soul.di.modue

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.soul.suhrob.soul.di.scopes.ViewModelKey
import com.soul.suhrob.soul.viewmodels.MainActivityViewModel

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun homeViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel
}