package com.rodrigotristany.mrjeff.internal.di.components

import android.content.Context
import com.rodrigotristany.mrjeff.data.api.GeoNamesApiService
import com.rodrigotristany.mrjeff.domain.cities.GetCitiesUseCase
import com.rodrigotristany.mrjeff.domain.cities.GetHistorySearchUseCase
import com.rodrigotristany.mrjeff.domain.weather.GetWeatherInfoUseCase
import com.rodrigotristany.mrjeff.internal.App
import com.rodrigotristany.mrjeff.internal.di.modules.DataModule
import com.rodrigotristany.mrjeff.internal.di.modules.DomainModule
import com.rodrigotristany.mrjeff.internal.di.modules.ApplicationModule
import com.rodrigotristany.mrjeff.internal.di.modules.UiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    DomainModule::class,
    DataModule::class,
    UiModule::class])
interface ApplicationComponent {
    fun inject(app: App)
    fun applicationContext(): Context
    fun geoNamesApiService(): GeoNamesApiService
    fun getCitiesUserCase(): GetCitiesUseCase
    fun getHistorySearchUseCase() : GetHistorySearchUseCase
    fun getWeatherInfoUseCase() : GetWeatherInfoUseCase
}