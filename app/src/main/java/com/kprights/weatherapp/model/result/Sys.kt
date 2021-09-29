package com.kprights.weatherapp.model.result

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Sys (

		@SerializedName("type") var type : Int = 0,
		@SerializedName("id") var id : Int = 0,
		@SerializedName("country") var country : String = "",
		@SerializedName("sunrise") var sunrise : Int = 0,
		@SerializedName("sunset") var sunset : Int = 0
)