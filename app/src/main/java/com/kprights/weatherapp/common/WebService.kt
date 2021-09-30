package com.kprights.weatherapp.common

import com.kprights.weatherapp.model.result.Root
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kprights.weatherapp.model.forecast.Base
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 30/9/21
 * Time : 12:29 AM
 */

private const val BASE_URL = "https://api.openweathermap.org/"
private const val API_KEY = "8b77325fb32353b57fbb0389a4d86b9f"

private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

val WebService: Api by lazy { retrofit.create(Api::class.java) }

interface Api
{
    @GET("data/2.5/weather?units=metric")
    fun getWeatherByCityName(@Query("q") q: String = "London", @Query("appid") appid: String = API_KEY): Deferred<Root>


    @GET("data/2.5/forecast?units=metric")
    fun getForecarstForFiveDaysByCity(@Query("q") q: String = "London", @Query("appid") appid: String = API_KEY): Deferred<Base>
}