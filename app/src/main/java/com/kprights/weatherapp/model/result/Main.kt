package com.kprights.weatherapp.model.result

import com.google.gson.annotations.SerializedName

data class Main (
		@SerializedName("temp") var temp : Double = 0.0,
		@SerializedName("feels_like") var feels_like : Double = 0.0,
		@SerializedName("temp_min") var temp_min : Double = 0.0,
		@SerializedName("temp_max") var temp_max : Double = 0.0,
		@SerializedName("pressure") var pressure : Int = 0,
		@SerializedName("humidity") var humidity : Int = 0
)