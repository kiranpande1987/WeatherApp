package com.kprights.weatherapp.model.result

import com.google.gson.annotations.SerializedName

data class Wind (
		@SerializedName("speed") var speed : Double = 0.0,
		@SerializedName("deg") var deg : Int = 0,
		@SerializedName("gust") var gust : Double = 0.0
)