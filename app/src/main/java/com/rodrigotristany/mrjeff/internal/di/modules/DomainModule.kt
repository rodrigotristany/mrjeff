package com.rodrigotristany.mrjeff.internal.di.modules

import com.rodrigotristany.mrjeff.data.cities.dao.CityDao
import com.rodrigotristany.mrjeff.data.cities.repository.CityRepository
import com.rodrigotristany.mrjeff.data.weather.repository.WeatherRepository
import com.rodrigotristany.mrjeff.domain.cities.GetCitiesUseCase
import com.rodrigotristany.mrjeff.domain.cities.GetHistorySearchUseCase
import com.rodrigotristany.mrjeff.domain.cities.SaveLastSearchUseCase
import com.rodrigotristany.mrjeff.domain.weather.GetWeatherInfoUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class DomainModule {
    @Provides
    @Singleton
    @Named("ioScheduler")
    internal fun provideIoScheduler() = Schedulers.io()

    @Provides
    @Singleton
    @Named("mainThreadScheduler")
    internal fun provideMainThreadScheduler() = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    internal fun provideGetCitiesUserCase(
        cityRepository: CityRepository,
        @Named("ioScheduler") ioScheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler): GetCitiesUseCase =
        GetCitiesUseCase(cityRepository, ioScheduler, mainThreadScheduler)

    @Provides
    @Singleton
    internal fun provideGetHistorySearchUseCase(
        cityDao: CityDao,
        @Named("ioScheduler") ioScheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler): GetHistorySearchUseCase =
        GetHistorySearchUseCase(cityDao, ioScheduler, mainThreadScheduler)

    @Provides
    @Singleton
    internal fun provideSaveLastSearchUseCase(
        cityDao: CityDao,
        @Named("ioScheduler") ioScheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler): SaveLastSearchUseCase =
        SaveLastSearchUseCase(cityDao, ioScheduler, mainThreadScheduler)

    @Provides
    @Singleton
    internal fun provideGetWeatherInfoUseCase(
        weatherRepository: WeatherRepository,
        @Named("ioScheduler") ioScheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler): GetWeatherInfoUseCase =
        GetWeatherInfoUseCase(weatherRepository, ioScheduler, mainThreadScheduler)
}