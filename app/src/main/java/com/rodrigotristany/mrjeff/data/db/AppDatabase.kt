package com.rodrigotristany.mrjeff.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rodrigotristany.mrjeff.data.cities.dao.CityDao
import com.rodrigotristany.mrjeff.data.cities.models.City

@Database(entities = [City::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
