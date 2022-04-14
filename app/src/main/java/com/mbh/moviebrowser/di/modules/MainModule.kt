package com.mbh.moviebrowser.di.modules

import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.di.ViewModelKey
import com.mbh.moviebrowser.features.movieList.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [MainModule.ViewModelModule::class])
class MainModule {
    @Module
    internal abstract class ViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(MovieListViewModel::class)
        internal abstract fun bindMovieListViewModel(viewModel: MovieListViewModel): ViewModel
    }
}