package com.rodrigotristany.mrjeff.data.api

import com.rodrigotristany.mrjeff.data.cities.models.CityResponse
import com.rodrigotristany.mrjeff.data.weather.models.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IGeoNamesAPI {
    @GET("searchJSON?maxRows=20&startRow=0&lang=en&isNameRequired=true&style=FULL&username=ilgeonamessample")
    fun getCities(@Query("q") q: String): Observable<CityResponse>

    @GET("weatherJSON?username=ilgeonamessample")
    fun getLocationWeather(@Query("north") north: Float,
                           @Query("south") south: Float,
                           @Query("east") east: Float,
                           @Query("west") west: Float): Observable<WeatherResponse>
}