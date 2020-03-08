package com.rodrigotristany.mrjeff.internal

import android.app.Application
import com.rodrigotristany.mrjeff.internal.di.modules.DataModule
import com.rodrigotristany.mrjeff.internal.di.modules.DomainModule
import com.rodrigotristany.mrjeff.internal.di.components.ApplicationComponent
import com.rodrigotristany.mrjeff.internal.di.components.DaggerApplicationComponent
import com.rodrigotristany.mrjeff.internal.di.modules.ApplicationModule
import com.rodrigotristany.mrjeff.internal.di.modules.UiModule

class App: Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        initInjector()
    }

    private fun initInjector() {
        applicationComponent.inject(this)
    }
}