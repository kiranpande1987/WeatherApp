package com.kprights.weatherapp.model.result

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Root (

		@SerializedName("coord") var coord : Coord = Coord(),
		@SerializedName("weather") var weather : List<Weather> = emptyList(),
		@SerializedName("base") var base : String = "",
		@SerializedName("main") var main : Main = Main(),
		@SerializedName("visibility") var visibility : Int = 0,
		@SerializedName("wind") var wind : Wind = Wind(),
		@SerializedName("clouds") var clouds : Clouds = Clouds(),
		@SerializedName("dt") var dt : Long = 0L,
		@SerializedName("sys") var sys : Sys = Sys(),
		@SerializedName("timezone") var timezone : Int = 0,
		@SerializedName("id") var id : Int = 0,
		@SerializedName("name") var name : String = "",
		@SerializedName("cod") var cod : Int = 0
)