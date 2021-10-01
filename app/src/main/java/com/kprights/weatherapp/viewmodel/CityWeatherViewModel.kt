package com.kprights.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kprights.weatherapp.model.forecast.Base
import com.kprights.weatherapp.model.result.Root
import kotlinx.coroutines.Dispatchers


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 30/9/21
 * Time : 1:22 AM
 */

class CityWeatherViewModel(
        private val cityWeatherRepository: CityWeatherRepository = CityWeatherRepository(remoteDataSource = RemoteDataSource(), ioDispatcher = Dispatchers.Main)
) : ViewModel() {
    val root: LiveData<Root> = Transformations.map(cityWeatherRepository.roots) { it }
    val base: LiveData<Base> = Transformations.map(cityWeatherRepository.bases) { it }
    val status: LiveData<ApiStatus> = Transformations.map(cityWeatherRepository.status) { it }

    override fun onCleared() {
        super.onCleared()
        cityWeatherRepository.cancel()
    }

    fun getWeatherByCityName(cityName: String) {
        cityWeatherRepository.updateDataFromRemoteDataSource(cityName)
    }
}