package com.mbh.moviebrowser.di.modules

import com.mbh.moviebrowser.di.modules.MainModule
import com.mbh.moviebrowser.features.movieDetails.MovieDetailsFragment
import com.mbh.moviebrowser.features.movieList.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
    abstract class MovieDetailsFragmentModule {
        @ContributesAndroidInjector(modules = [MainModule::class])
        abstract fun contributeDetailsFragment(): MovieDetailsFragment
    }