package com.kprights.weatherapp.model.forecast

import com.google.gson.annotations.SerializedName

data class Clouds (

	@SerializedName("all") var all : Int = 0
)