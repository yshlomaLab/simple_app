package com.intelity.test

import android.app.Application
import com.intelity.test.di.component.DaggerMainComponent
import com.intelity.test.di.component.MainComponent
import com.intelity.test.di.modules.DataModule
import com.intelity.test.di.modules.NetworkModule

/**
 * Application class has main component to inject it in view model.
 */
class App : Application() {

    private lateinit var mainComponent: MainComponent

    override fun onCreate() {
        super.onCreate()

        mainComponent = DaggerMainComponent.builder()
            .dataModule(DataModule(this))
            .networkModule(NetworkModule())
            .build()
    }

    fun provideMainComponent(): MainComponent {
        return mainComponent
    }

}