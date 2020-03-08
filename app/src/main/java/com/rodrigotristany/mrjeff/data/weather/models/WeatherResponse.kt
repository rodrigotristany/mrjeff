package com.rodrigotristany.mrjeff.data.weather.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherResponse(
    @SerializedName("weatherObservations")
    @Expose
    val weatherObservations: List<WeatherObservation>
)