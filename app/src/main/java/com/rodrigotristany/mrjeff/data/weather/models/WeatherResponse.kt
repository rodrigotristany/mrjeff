package com.rodrigotristany.mrjeff.data.weather.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rodrigotristany.mrjeff.data.api.Status

class WeatherResponse(
    @SerializedName("weatherObservations")
    @Expose
    val weatherObservations: List<WeatherObservation>,

    @SerializedName("status")
    @Expose
    val status: Status?) {

    fun statusOk() = status == null
}