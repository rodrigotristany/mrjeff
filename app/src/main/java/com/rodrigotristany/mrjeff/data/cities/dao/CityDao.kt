package com.rodrigotristany.mrjeff.data.cities.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodrigotristany.mrjeff.data.cities.models.City
import io.reactivex.Single

@Dao
interface CityDao {
    @Query("SELECT * From city")
    fun recentSearch(): Single<List<City>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSearch(city: City) : Single<Long>
}