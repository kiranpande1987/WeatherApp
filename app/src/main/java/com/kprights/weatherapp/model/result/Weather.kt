package com.kprights.weatherapp.model.result

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Weather (

		@SerializedName("id") var id : Int = 0,
		@SerializedName("main") var main : String = "",
		@SerializedName("description") var description : String = "",
		@SerializedName("icon") var icon : String = ""
)