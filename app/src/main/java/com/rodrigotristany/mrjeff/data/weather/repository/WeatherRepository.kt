package com.rodrigotristany.mrjeff.data.weather.repository

import com.rodrigotristany.mrjeff.data.api.GeoNamesApiService
import com.rodrigotristany.mrjeff.data.weather.models.WeatherRequest
import com.rodrigotristany.mrjeff.data.weather.models.WeatherResponse
import io.reactivex.Observable
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val geoNamesApiService: GeoNamesApiService) {
    fun weatherObservation(weatherRequest: WeatherRequest): Observable<WeatherResponse> = geoNamesApiService.getLocationWeather(weatherRequest.north, weatherRequest.south, weatherRequest.east, weatherRequest.west)
        .map {
            it
        }
}