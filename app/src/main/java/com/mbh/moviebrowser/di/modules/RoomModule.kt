package com.mbh.moviebrowser.di.modules

import android.app.Application
import com.mbh.moviebrowser.data.db.MovieDao
import com.mbh.moviebrowser.data.db.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(application: Application) {

    private var movieApplication = application
    private lateinit var movieDatabase: MovieDatabase

    @Singleton
    @Provides
    fun providesRoomDatabase(): MovieDatabase {
        movieDatabase = MovieDatabase.getInstance(movieApplication)
        return movieDatabase
    }

    @Singleton
    @Provides
    fun providesMovieDao(md: MovieDatabase): MovieDao {
        return md.movieDao()
    }

}