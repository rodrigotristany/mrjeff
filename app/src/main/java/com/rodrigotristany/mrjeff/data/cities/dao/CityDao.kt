package com.rodrigotristany.mrjeff.data.cities.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rodrigotristany.mrjeff.data.cities.models.City

@Dao
interface CityDao {
    @Query("SELECT * From city")
    fun recentSearch(): List<City>

    @Insert
    fun saveSearch(city: City)
}