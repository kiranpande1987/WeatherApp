package com.kprights.weatherapp.model.result

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Clouds (
	@SerializedName("all") var all : Int = 0
)