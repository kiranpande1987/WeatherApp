package com.kprights.weatherapp.model.forecast

import com.google.gson.annotations.SerializedName

data class Detail(
		@SerializedName("dt") var dt : Long = 0L,
		@SerializedName("main") var main : Main = Main(),
		@SerializedName("weather") var weather : List<Weather> = emptyList(),
		@SerializedName("clouds") var clouds : Clouds = Clouds(),
		@SerializedName("wind") var wind : Wind = Wind(),
		@SerializedName("visibility") var visibility : Int = 0,
		@SerializedName("pop") var pop : Double = 0.0,
		@SerializedName("sys") var sys : Sys = Sys(),
		@SerializedName("dt_txt") var dt_txt : String = ""
)