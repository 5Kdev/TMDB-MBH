package com.mbh.moviebrowser.di.modules

import android.content.Context
import com.mbh.moviebrowser.MovieApplication
import com.mbh.moviebrowser.di.ActivityBuildersModule
import com.mbh.moviebrowser.di.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule


@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        RoomModule::class,
        ViewModelFactoryModule::class,
        ActivityBuildersModule::class,
        MovieListFragmentModule::class,
        MovieDetailsFragmentModule::class,
    ]
)
class AppModule {

    private var app: MovieApplication? = null

    fun AppModule(app: MovieApplication?) {
        this.app = app
    }

    @Provides
    fun providesApp(): MovieApplication? {
        return app
    }

    @Provides
    fun getAppContext(): Context? {
        return app
    }
}
