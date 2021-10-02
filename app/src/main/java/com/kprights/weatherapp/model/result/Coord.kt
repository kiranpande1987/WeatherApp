package com.kprights.weatherapp.model.result

import com.google.gson.annotations.SerializedName

data class Coord (
		@SerializedName("lon") var lon : Double = 0.0,
		@SerializedName("lat") var lat : Double = 0.0
)