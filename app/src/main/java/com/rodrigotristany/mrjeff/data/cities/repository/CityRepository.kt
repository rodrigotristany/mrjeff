package com.rodrigotristany.mrjeff.data.cities.repository

import com.rodrigotristany.mrjeff.data.api.GeoNamesApiService
import com.rodrigotristany.mrjeff.data.cities.models.CityResponse
import io.reactivex.Observable
import javax.inject.Inject

class CityRepository @Inject constructor(private val geoNamesApiService: GeoNamesApiService) {
    fun cities(search: String) : Observable<CityResponse> = geoNamesApiService.getCities(search)
        .map {
            it
        }
}