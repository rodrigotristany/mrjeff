package com.rodrigotristany.mrjeff.data.weather.repository

import com.rodrigotristany.mrjeff.data.api.GeoNamesApiService
import com.rodrigotristany.mrjeff.data.weather.models.WeatherRequest
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val geoNamesApiService: GeoNamesApiService) {
    fun weatherObservation(weatherRequest: WeatherRequest)
            = geoNamesApiService.getLocationWeather(weatherRequest.north, weatherRequest.south, weatherRequest.east, weatherRequest.west)
        .map {
            it.weatherObservations
        }
}