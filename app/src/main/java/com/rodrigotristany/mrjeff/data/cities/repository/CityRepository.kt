package com.rodrigotristany.mrjeff.data.cities.repository

import com.rodrigotristany.mrjeff.data.cities.models.City
import com.rodrigotristany.mrjeff.data.api.IGeoNamesAPI
import io.reactivex.Observable
import javax.inject.Inject

class CityRepository @Inject constructor(private val geoNamesAPI: IGeoNamesAPI) {
    fun cities(search: String) : Observable<List<City>> = geoNamesAPI.getCities(search)
        .map {
            it.cities
        }
}