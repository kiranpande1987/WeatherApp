package com.kprights.weatherapp.model.forecast

import com.google.gson.annotations.SerializedName

data class Main (
		@SerializedName("temp") var temp : Double = 0.0,
		@SerializedName("feels_like") var feels_like : Double = 0.0,
		@SerializedName("temp_min") var temp_min : Double = 0.0,
		@SerializedName("temp_max") var temp_max : Double = 0.0,
		@SerializedName("pressure") var pressure : Int = 0,
		@SerializedName("sea_level") var sea_level : Int = 0,
		@SerializedName("grnd_level") var grnd_level : Int = 0,
		@SerializedName("humidity") var humidity : Int = 0,
		@SerializedName("temp_kf") var temp_kf : Double = 0.0
)