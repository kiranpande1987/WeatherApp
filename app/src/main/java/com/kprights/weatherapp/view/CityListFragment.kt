package com.kprights.weatherapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kprights.weatherapp.common.bindRecyclerView
import com.kprights.weatherapp.common.getCitiesAndCountriesFromJSON
import com.kprights.weatherapp.databinding.FragmentCityListBinding
import com.kprights.weatherapp.viewmodel.CityWeatherRepository
import com.kprights.weatherapp.viewmodel.CityWeatherViewModel
import com.kprights.weatherapp.viewmodel.RemoteDataSource
import kotlinx.coroutines.Dispatchers


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/9/21
 * Time : 12:46 PM
 */

class CityListFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentCityListBinding.inflate(inflater)

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
        })

        binding.recyclerViewForCityList.adapter = CityListAdapter(CityListAdapter.OnClickListener {

            model.getWeatherByCityName(it)

            Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
        })

        this.context?.let {
            bindRecyclerView(binding.recyclerViewForCityList, getCitiesAndCountriesFromJSON(it, ""))
        }

        binding.editTextSearchCity.setOnEditorActionListener { editText, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH)
                bindRecyclerView(binding.recyclerViewForCityList, getCitiesAndCountriesFromJSON(requireContext(), editText.text.toString()))

            return@setOnEditorActionListener true
        }

        return binding.root
    }
}