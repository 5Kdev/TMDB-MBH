package com.mbh.moviebrowser.di

import android.content.Context
import com.mbh.moviebrowser.MainActivity
import com.mbh.moviebrowser.di.modules.MainModule
import com.mbh.moviebrowser.di.modules.MovieListFragmentModule
import com.mbh.moviebrowser.features.movieList.MovieListFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules = [MainModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}
