package com.kprights.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import com.kprights.weatherapp.common.WebService
import com.kprights.weatherapp.model.result.Root
import timber.log.Timber


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
        val root = deferred.await()
        return root
    }

    override fun getCityWeatherFromLocal(): LiveData<Root> {
        TODO("Not yet implemented")
    }
}