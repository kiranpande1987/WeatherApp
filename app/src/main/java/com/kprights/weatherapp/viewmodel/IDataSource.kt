package com.kprights.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import com.kprights.weatherapp.model.result.Root


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 30/9/21
 * Time : 12:59 AM
 */

interface IDataSource {
    suspend fun getCityWeatherFromRemote(cityName: String): Root
    fun getCityWeatherFromLocal(): LiveData<Root>
}