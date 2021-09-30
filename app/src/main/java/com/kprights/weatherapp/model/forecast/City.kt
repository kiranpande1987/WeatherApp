package com.kprights.weatherapp.model.forecast

import com.google.gson.annotations.SerializedName


data class City (

		@SerializedName("id") var id : Int = 0,
		@SerializedName("name") var name : String = "",
		@SerializedName("coord") var coord : Coord = Coord(),
		@SerializedName("country") var country : String = "",
		@SerializedName("population") var population : Int = 0,
		@SerializedName("timezone") var timezone : Int = 0,
		@SerializedName("sunrise") var sunrise : Long = 0L,
		@SerializedName("sunset") var sunset : Long = 0L
)