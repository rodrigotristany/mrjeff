package com.rodrigotristany.mrjeff.data.cities.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rodrigotristany.mrjeff.data.api.Status

class CityResponse(
    @SerializedName("geonames")
    @Expose
    val cities: List<City>,

    @SerializedName("status")
    @Expose
    val status: Status?) {

    fun statusOk() = status == null
}