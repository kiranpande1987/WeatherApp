package com.kprights.weatherapp.model.forecast

import com.google.gson.annotations.SerializedName

data class Base (
		@SerializedName("cod") var cod : Int = 0,
		@SerializedName("message") var message : Int = 0,
		@SerializedName("cnt") var cnt : Int = 0,
		@SerializedName("list") var list : List<Detail>,
		@SerializedName("city") var city : City = City()
)