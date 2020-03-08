package com.rodrigotristany.mrjeff.data.cities.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    val lat: String,

    @ColumnInfo(name = "lng")
    val lng: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "population")
    val population: Int,

    @PrimaryKey
    val geonameId: Int,

    @ColumnInfo(name = "timezone")
    val timezone: Timezone
)