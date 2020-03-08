package com.rodrigotristany.mrjeff.data.weather.repository

import com.rodrigotristany.mrjeff.data.api.IGeoNamesAPI
import com.rodrigotristany.mrjeff.data.weather.models.WeatherRequest
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val geoNamesAPI: IGeoNamesAPI) {
    fun weatherObservation(weatherRequest: WeatherRequest)
            = geoNamesAPI.getLocationWeather(weatherRequest.north, weatherRequest.south, weatherRequest.east, weatherRequest.west)
        .map {
            it.weatherObservations
        }
}