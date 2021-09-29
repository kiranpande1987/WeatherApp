package com.kprights.weatherapp

import android.content.res.AssetManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.kprights.weatherapp.model.Base
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCitiesAndCountriesFromAssets()
    }

    private fun getCitiesAndCountriesFromAssets() {
        val json = this.assets.readAssetsFile("cities.json")

        sampletext.text = "Timber"

        val gson = Gson()
        val m: Base = gson.fromJson(json, Base::class.java)
        sampletext.text = m.msg
        sampletext.text = m.data?.get(0)?.country

        // Timber.e(json)
    }
}

fun AssetManager.readAssetsFile(fileName: String): String = open(fileName).bufferedReader().use{it.readText()}