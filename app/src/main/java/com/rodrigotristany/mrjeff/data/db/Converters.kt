package com.rodrigotristany.mrjeff.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rodrigotristany.mrjeff.data.cities.models.Bbox
import com.rodrigotristany.mrjeff.data.cities.models.Timezone

class Converters {

    private val gson : Gson = Gson()

    @TypeConverter
    fun fromBbox(value: Bbox): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toBbox(json: String): Bbox {
        return gson.fromJson(json, Bbox::class.java)
    }

    @TypeConverter
    fun fromTimezon(value: Timezone): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toTimezone(json: String): Timezone {
        return gson.fromJson(json, Timezone::class.java)
    }

}