package com.intelity.test.di.component

import com.intelity.test.di.modules.DataModule
import com.intelity.test.di.modules.NetworkModule
import com.intelity.test.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DataModule::class])
interface MainComponent {

    fun inject(viewModel: MainViewModel)
}