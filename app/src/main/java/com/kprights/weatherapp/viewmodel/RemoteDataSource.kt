package com.kprights.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import com.kprights.weatherapp.common.WebService
import com.kprights.weatherapp.model.forecast.Base
import com.kprights.weatherapp.model.result.Root

/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 30/9/21
 * Time : 12:54 AM
 */

class RemoteDataSource: IDataSource {

    override suspend fun getCityWeatherFromRemote(cityName: String): Root {
        val deferred = WebService.getWeatherByCityName(cityName)
        return deferred.await()
    }

    override suspend fun getForecastForFiveDaysByCityFromRemote(cityName: String): Base {
        val deferred = WebService.getForecarstForFiveDaysByCity(cityName)
        return deferred.await()
    }

    override fun getCityWeatherFromLocal(): LiveData<Root> {
        TODO("Not yet implemented")
    }
}