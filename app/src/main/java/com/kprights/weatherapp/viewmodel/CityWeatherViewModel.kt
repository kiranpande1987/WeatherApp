package com.kprights.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kprights.weatherapp.model.result.Root


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 30/9/21
 * Time : 1:22 AM
 */

class CityWeatherViewModel(
        val cityWeatherRepository: CityWeatherRepository
) : ViewModel() {
    val root: LiveData<Root> = Transformations.map(cityWeatherRepository.roots) { it }
    val status: LiveData<ApiStatus> = Transformations.map(cityWeatherRepository.status) { it }

    override fun onCleared() {
        super.onCleared()
        cityWeatherRepository.cancel()
    }

    fun getWeatherByCityName(cityName: String) {
        cityWeatherRepository.updateDataFromRemoteDataSource(cityName)
    }
}