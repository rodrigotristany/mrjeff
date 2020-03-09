package com.rodrigotristany.mrjeff.data.api

import com.rodrigotristany.mrjeff.data.cities.models.CityResponse
import com.rodrigotristany.mrjeff.data.weather.models.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoNamesApiService {
    @GET("searchJSON?maxRows=20&startRow=0&lang=en&isNameRequired=true&style=FULL&username=ilgeonamessample")
    fun getCities(@Query("q") q: String): Observable<CityResponse>

    @GET("weatherJSON?username=ilgeonamessample")
    fun getLocationWeather(@Query("north") north: Double?,
                           @Query("south") south: Double?,
                           @Query("east") east: Double?,
                           @Query("west") west: Double?): Observable<WeatherResponse>
}