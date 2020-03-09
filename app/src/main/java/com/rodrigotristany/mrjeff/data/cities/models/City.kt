package com.rodrigotristany.mrjeff.data.cities.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity(tableName = "city")
data class City(
    @ColumnInfo(name = "asciiName")
    val asciiName: String,

    @ColumnInfo(name = "bbox")
    val bbox: Bbox,

    @ColumnInfo(name = "countryCode")
    val countryCode: String,

    @ColumnInfo(name = "countryId")
    val countryId: String,

    @ColumnInfo(name = "countryName")
    val countryName: String,

    @ColumnInfo(name = "lat")
    val lat: Double,

    @ColumnInfo(name = "lng")
    val lng: Double,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "population")
    val population: Int,

    @PrimaryKey
    val geonameId: Int,

    @ColumnInfo(name = "timezone")
    val timezone: Timezone
)