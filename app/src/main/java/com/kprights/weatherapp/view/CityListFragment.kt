package com.kprights.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kprights.weatherapp.common.bindRecyclerView
import com.kprights.weatherapp.common.getCitiesAndCountriesFromJSON
import com.kprights.weatherapp.databinding.FragmentCityListBinding


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

        binding.recyclerViewForCityList.adapter = CityListAdapter(CityListAdapter.OnClickListener {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}