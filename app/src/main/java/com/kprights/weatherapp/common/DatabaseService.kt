package com.kprights.weatherapp.common

import com.kprights.weatherapp.model.result.Root
import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.lifecycle.LiveData
import androidx.room.*
import com.kprights.weatherapp.model.result.Convertors


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 30/9/21
 * Time : 12:29 AM
 */

//@Database(entities = [Root::class], version = 1, exportSchema = false)
//@TypeConverters(Convertors::class)
//abstract class DatabaseService : RoomDatabase()
//{
//    abstract val cityWeatherDao: CityWeatherDao
//
//    companion object{
//        @Volatile
//        private var INSTANCE: DatabaseService? = null
//
//        fun getInstance(context: Context) : DatabaseService
//        {
//            var instance = INSTANCE
//
//            if(instance == null)
//            {
//                instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        DatabaseService::class.java,
//                        "city_weather_database"
//                ).fallbackToDestructiveMigration()
//                        .build()
//
//                INSTANCE = instance
//            }
//
//            return instance
//        }
//    }
//}
//
//@Dao
//interface CityWeatherDao
//{
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(root: Root)
//
//    @Query("DELETE FROM Root")
//    suspend fun clear()
//
//    @Query("SELECT * FROM Root")
//    fun getAllWeather(): LiveData<Root>
//
//    @Query("SELECT * FROM Root")
//    fun getCityWeather(): Root
//}