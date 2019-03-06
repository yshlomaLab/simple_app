package com.intelity.test.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.intelity.test.App
import com.intelity.test.di.component.MainComponent

/**
 * Base view model to inject application components.
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val injector: MainComponent = (application as App).provideMainComponent()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is MainViewModel -> injector.inject(this)
        }
    }
}