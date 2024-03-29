package com.kprights.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kprights.weatherapp.common.DEFAULT_CITY
import com.kprights.weatherapp.model.forecast.Base
import com.kprights.weatherapp.model.result.Root
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 30/9/21
 * Time : 12:58 AM
 */

enum class ApiStatus { NO_INTERNET, LOADING, ERROR, DONE }

class CityWeatherRepository(
        private val remoteDataSource: IDataSource,
        private val ioDispatcher: CoroutineDispatcher = Dispatchers.Main
) {

    private val job = Job()
    private val scope = CoroutineScope(job + ioDispatcher)

    var roots: MutableLiveData<Root> = MutableLiveData<Root>()
    var bases: MutableLiveData<Base> = MutableLiveData<Base>()
    val status: MutableLiveData<ApiStatus> = MutableLiveData<ApiStatus>()

    init {
        updateForecastDataFromRemoteDataSource(DEFAULT_CITY)
    }

    fun updateForecastDataFromRemoteDataSource(cityName: String) {
        scope.launch(ioDispatcher) {

            val base = fetchForecastDataFromRemote(cityName)

            base?.let {
                status.postValue(ApiStatus.DONE)
                bases.postValue(base)
            }
        }
    }

    fun updateWeatherDataFromRemoteDataSource(cityName: String) {
        scope.launch(ioDispatcher) {

            val root = fetchDataFromRemote(cityName)

            root?.let {
                status.postValue(ApiStatus.DONE)
                roots.postValue(root)
            }
        }
    }

    private suspend fun fetchDataFromRemote(cityName: String): Root? {
        try {
            status.postValue(ApiStatus.LOADING)
            return remoteDataSource.getCityWeatherFromRemote(cityName)
        } catch (e: Exception) {
            status.postValue(ApiStatus.ERROR)
        }

        return null
    }

    private suspend fun fetchForecastDataFromRemote(cityName: String): Base? {
        try {
            status.postValue(ApiStatus.LOADING)
            return remoteDataSource.getForecastForFiveDaysByCityFromRemote(cityName)
        } catch (e: UnknownHostException){
            status.postValue(ApiStatus.NO_INTERNET)
        } catch (e: HttpException){
            status.postValue(ApiStatus.ERROR)
        } catch (e: Exception) {
            status.postValue(ApiStatus.ERROR)
        }

        return null
    }

    fun cancel() {
        job.cancel()
    }
}