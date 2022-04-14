package com.mbh.moviebrowser.di

import android.app.Application
import com.mbh.moviebrowser.MovieApplication
import com.mbh.moviebrowser.di.modules.AppModule
import com.mbh.moviebrowser.di.modules.RoomModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AppModule::class]
)

interface AppComponent : AndroidInjector<MovieApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun roomModule(roomModule: RoomModule): Builder

        fun build(): AppComponent
    }

    override fun inject(app: MovieApplication?)


}