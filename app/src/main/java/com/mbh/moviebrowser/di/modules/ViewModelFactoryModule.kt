package com.mbh.moviebrowser.di.modules

import androidx.lifecycle.ViewModelProvider
import com.mbh.moviebrowser.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
