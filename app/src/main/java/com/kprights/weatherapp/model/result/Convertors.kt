package com.kprights.weatherapp.model.result

import androidx.room.TypeConverter
import com.google.gson.Gson


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 30/9/21
 * Time : 1:36 AM
 */

class Convertors
{
    @TypeConverter
    fun rootToJson(value: Root) = Gson().toJson(value)

    @TypeConverter
    fun jsonToRoot(value: String) = Gson().fromJson(value, Root::class.java)
}