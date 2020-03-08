package com.rodrigotristany.mrjeff.data.weather.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherObservation(
    @SerializedName("ICAO")
    @Expose
    val icao: String,

    @SerializedName("clouds")
    @Expose
    val clouds: String,

    @SerializedName("datetime")
    @Expose
    val datetime: String,

    @SerializedName("dewPoint")
    @Expose
    val dewPoint: String,

    @SerializedName("humidity")
    @Expose
    val humidity: Int,

    @SerializedName("lat")
    @Expose
    val lat: Double,

    @SerializedName("lng")
    @Expose
    val lng: Double,

    @SerializedName("observation")
    @Expose
    val observation: String,

    @SerializedName("stationName")
    @Expose
    val stationName: String,

    @SerializedName("temperature")
    @Expose
    val temperature: String,

    @SerializedName("weatherCondition")
    @Expose
    val weatherCondition: String,

    @SerializedName("windDirection")
    @Expose
    val windDirection: Int,

    @SerializedName("windSpeed")
    @Expose
    val windSpeed: String
)