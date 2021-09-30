package com.kprights.weatherapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kprights.weatherapp.databinding.FragmentCityWeatherBinding
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentCityWeatherBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this



        val remoteDataSource = RemoteDataSource()

        val cityWeatherRepository = CityWeatherRepository(
                remoteDataSource = remoteDataSource,
                ioDispatcher = Dispatchers.Main
        )

        val model = CityWeatherViewModel(cityWeatherRepository)

        model.root.observe(viewLifecycleOwner, Observer {
            Log.e("CITTYYY 1", "OBserver called")
            Log.e("CITTYYY 2", "${it.toString()}")

            binding.cityWeather = it

        })

        return binding.root
    }
}