package com.mbh.moviebrowser

import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.mbh.moviebrowser.di.AppComponent
import com.mbh.moviebrowser.di.DaggerAppComponent
import com.mbh.moviebrowser.di.modules.AppModule
import com.mbh.moviebrowser.di.modules.RoomModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class MovieApplication: DaggerApplication() {
    lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .roomModule(RoomModule(this))
            .build()

        return appComponent
    }





}

