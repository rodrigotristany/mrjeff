package com.rodrigotristany.mrjeff.data.cities.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CityResponse(
    @SerializedName("geonames")
    @Expose
    val cities: List<City>
)