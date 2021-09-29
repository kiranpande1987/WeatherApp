package com.kprights.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import com.kprights.weatherapp.model.result.Root


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 30/9/21
 * Time : 1:00 AM
 */

//class LocalDataSource(private val database: CityWeatherDao): IDataSource {
//    override fun getCityWeatherFromLocal(): LiveData<Root> {
//        return database.getAllWeather()
//    }
//
//    suspend fun deleteWeatherForCity() {
//        database.clear()
//    }
//
//    suspend fun saveWeatherForCity(root: Root) {
//        database.insert(root)
//    }
//
//    override suspend fun getCityWeatherFromRemote(): Root {
//        TODO("Not yet implemented")
//    }
//}