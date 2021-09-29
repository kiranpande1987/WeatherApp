package com.kprights.weatherapp.model

import com.google.gson.annotations.SerializedName


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/9/21
 * Time : 1:04 PM
 */

data class Country(
    val country: String? = "",
    val cities: List<String>? = emptyList()
)

data class Base(
    val error: Boolean? = false,
    val msg: String? = "",
    val data: List<Country>? = emptyList()
)