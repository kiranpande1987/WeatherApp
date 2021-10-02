package com.kprights.weatherapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kprights.weatherapp.R
import com.kprights.weatherapp.databinding.FragmentCityWeatherBinding
import com.kprights.weatherapp.viewmodel.ApiStatus
import com.kprights.weatherapp.viewmodel.CityWeatherRepository
import com.kprights.weatherapp.viewmodel.CityWeatherViewModel
import com.kprights.weatherapp.viewmodel.RemoteDataSource
import kotlinx.coroutines.Dispatchers


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/9/21
 * Time : 11:36 PM
 */

class CityWeatherFragment: Fragment() {
    lateinit var model: CityWeatherViewModel
    lateinit var binding: FragmentCityWeatherBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCityWeatherBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        model = CityWeatherViewModel()

        model.status.observe(viewLifecycleOwner, Observer {
            binding.apiStatus = it
            if(it == ApiStatus.ERROR) Toast.makeText(context, "No City Found", Toast.LENGTH_SHORT).show()
        })

        model.root.observe(viewLifecycleOwner, Observer {
            Log.e("CITTYYY 1", "OBserver called")
            Log.e("CITTYYY 2", "${it.toString()}")

            binding.cityWeather = it

            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            val currentFavCities = sharedPref?.getString("FavouriteCities", null)

            if(currentFavCities?.contains("${it.name}, ${it.sys.country}") == true){
                binding.addFavourite.setBackgroundResource(R.drawable.ic_added_fav)
                binding.addFavourite.contentDescription = "FAVOURITES"
            } else {
                binding.addFavourite.setBackgroundResource(R.drawable.ic_add_fav)
                binding.addFavourite.contentDescription = ""
            }
        })

        model.base.observe(viewLifecycleOwner, Observer {
            Log.e("CITTYYY 1", "OBserver called")
            Log.e("CITTYYY 2", "${it.toString()}")

            binding.forecast = it

        })

        binding.addFavourite.setOnClickListener {
            if(it.contentDescription.toString().equals("FAVOURITES", ignoreCase = true)){
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                val currentFavCities = sharedPref?.getString("FavouriteCities", null)
                val cityName = binding.location.text.toString()

                with(sharedPref?.edit()) {

                    if(currentFavCities?.contains(binding.location.text) == true){
                        if(currentFavCities.equals(cityName, ignoreCase = true)){
                            this?.remove("FavouriteCities")
                            this?.apply()
                        } else {
                            var removedString = currentFavCities.replace(":$cityName", "")

                            if(removedString.equals(currentFavCities, true))
                                removedString = currentFavCities.replace("$cityName:", "")
                            this?.putString("FavouriteCities", removedString)
                            this?.apply()
                        }
                    }
                }

                it.setBackgroundResource(R.drawable.ic_add_fav)
                it.contentDescription = ""
            } else {
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                val currentFavCities = sharedPref?.getString("FavouriteCities", null)

                with(sharedPref?.edit()) {

                    if(currentFavCities.isNullOrEmpty())
                        this?.putString("FavouriteCities", "${binding.location.text}")
                    else
                        this?.putString("FavouriteCities", "$currentFavCities:${binding.location.text}")
                    this?.apply()
                }

                it.setBackgroundResource(R.drawable.ic_added_fav)
                it.contentDescription = "FAVOURITES"
            }
        }

        binding.searchCity.setOnEditorActionListener { textView, action, _ ->
            if(action == EditorInfo.IME_ACTION_SEARCH){
                val searchString = textView.text
                findWeatherForCityName(searchString.toString())
            }

            binding.searchCity.clearFocus()
            val kb: InputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            kb.hideSoftInputFromWindow(binding.searchCity.windowToken, 0)
            return@setOnEditorActionListener true
        }

        return binding.root
    }

    fun loadWeatherForCityName(cityName: String) {
        binding.searchCity.setText("")
        model.getForecastByCityName(cityName)
    }

    fun findWeatherForCityName(cityName: String) {
        model.getForecastByCityName(cityName)
    }
}