package com.kprights.weatherapp.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kprights.weatherapp.R
import com.kprights.weatherapp.common.CONTENT_DESCRIPTION_ADD_FAVOURITE
import com.kprights.weatherapp.common.EMPTY_STRING
import com.kprights.weatherapp.common.SHARED_PREFERENCE_FAV_CITY_KEY_NAME
import com.kprights.weatherapp.databinding.FragmentCityWeatherBinding
import com.kprights.weatherapp.viewmodel.ApiStatus
import com.kprights.weatherapp.viewmodel.CityWeatherViewModel

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCityWeatherBinding.inflate(inflater)

        binding.lifecycleOwner = this

        model = CityWeatherViewModel()

        model.status.observe(viewLifecycleOwner, Observer {
            binding.apiStatus = it
            if (it == ApiStatus.ERROR) Toast.makeText(
                context,
                R.string.no_city_found,
                Toast.LENGTH_SHORT
            ).show()
            if (it == ApiStatus.NO_INTERNET) {
                val alertDialog = AlertDialog.Builder(context).create()
                alertDialog.setTitle(R.string.no_internet_title)
                alertDialog.setCancelable(false)
                alertDialog.setMessage(resources.getString(R.string.no_internet_message))
                alertDialog.setButton(
                    AlertDialog.BUTTON_NEUTRAL, resources.getString(R.string.exit)
                )
                {
                        dialog, _ ->
                            dialog.dismiss()
                            activity?.finishAndRemoveTask()
                }
                alertDialog.show()
            }
        })

        model.base.observe(viewLifecycleOwner, Observer {
            binding.forecast = it

            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            val currentFavCities = sharedPref?.getString(SHARED_PREFERENCE_FAV_CITY_KEY_NAME, null)

            if (currentFavCities?.contains("${it.city.name}, ${it.city.country}") == true) {
                binding.addFavourite.setBackgroundResource(R.drawable.ic_added_fav)
                binding.addFavourite.contentDescription = CONTENT_DESCRIPTION_ADD_FAVOURITE
            } else {
                binding.addFavourite.setBackgroundResource(R.drawable.ic_add_fav)
                binding.addFavourite.contentDescription = EMPTY_STRING
            }
        })

        binding.addFavourite.setOnClickListener {
            if(it.contentDescription.toString().equals(
                    CONTENT_DESCRIPTION_ADD_FAVOURITE,
                    ignoreCase = true
                )){
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                val currentFavCities = sharedPref?.getString(
                    SHARED_PREFERENCE_FAV_CITY_KEY_NAME,
                    null
                )
                val cityName = binding.location.text.toString()

                with(sharedPref?.edit()) {

                    if(currentFavCities?.contains(binding.location.text) == true){
                        if(currentFavCities.equals(cityName, ignoreCase = true)){
                            this?.remove(SHARED_PREFERENCE_FAV_CITY_KEY_NAME)
                            this?.apply()
                        } else {
                            var removedString = currentFavCities.replace(":$cityName", EMPTY_STRING)

                            if(removedString.equals(currentFavCities, true))
                                removedString = currentFavCities.replace("$cityName:", EMPTY_STRING)
                            this?.putString(SHARED_PREFERENCE_FAV_CITY_KEY_NAME, removedString)
                            this?.apply()
                        }
                    }
                }

                it.setBackgroundResource(R.drawable.ic_add_fav)
                it.contentDescription = EMPTY_STRING
            } else {
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                val currentFavCities = sharedPref?.getString(
                    SHARED_PREFERENCE_FAV_CITY_KEY_NAME,
                    null
                )

                with(sharedPref?.edit()) {

                    if(currentFavCities.isNullOrEmpty())
                        this?.putString(
                            SHARED_PREFERENCE_FAV_CITY_KEY_NAME,
                            "${binding.location.text}"
                        )
                    else
                        this?.putString(
                            SHARED_PREFERENCE_FAV_CITY_KEY_NAME,
                            "$currentFavCities:${binding.location.text}"
                        )
                    this?.apply()
                }

                it.setBackgroundResource(R.drawable.ic_added_fav)
                it.contentDescription = CONTENT_DESCRIPTION_ADD_FAVOURITE
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
        binding.searchCity.setText(EMPTY_STRING)
        model.getForecastByCityName(cityName)
    }

    private fun findWeatherForCityName(cityName: String) {
        model.getForecastByCityName(cityName)
    }
}