package com.rodrigotristany.mrjeff.ui.maps.di

import android.content.Context
import com.rodrigotristany.mrjeff.domain.cities.SaveLastSearchUseCase
import com.rodrigotristany.mrjeff.domain.weather.GetWeatherInfoUseCase
import com.rodrigotristany.mrjeff.internal.di.scope.PerActivity
import com.rodrigotristany.mrjeff.ui.maps.MapsMVP
import com.rodrigotristany.mrjeff.ui.maps.MapsPresenter
import dagger.Module
import dagger.Provides

@Module
class MapsModule {
    @PerActivity
    @Provides
    fun provideMapsPresenter(getWeatherInfoUseCase: GetWeatherInfoUseCase,
                             saveLastSearchUseCase: SaveLastSearchUseCase,
                             context: Context) : MapsMVP.Presenter {
        return MapsPresenter(getWeatherInfoUseCase, saveLastSearchUseCase, context)
    }
}