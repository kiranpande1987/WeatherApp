package com.kprights.weatherapp.common

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.google.gson.Gson
import com.kprights.weatherapp.model.Base


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/9/21
 * Time : 4:27 PM
 */

var root: Base? = null

fun getCitiesAndCountriesFromJSON(context: Context, searchString: String): List<String> {

    val allCities = mutableListOf<String>()

    if(root == null){
        val json = context.readJSONFromAssets()
        root = Gson().fromJson(json, Base::class.java)
    }

    root?.data?.forEach {
            country ->
                country.cities?.forEach {
                    city -> if(city.contains(searchString, ignoreCase = true)) allCities.add("$city, ${country.country}")
                }
    }

    return allCities
}

fun Context.readJSONFromAssets() = assets.readAssetsFile("cities.json")

fun AssetManager.readAssetsFile(fileName: String): String = open(fileName).bufferedReader().use{it.readText()}