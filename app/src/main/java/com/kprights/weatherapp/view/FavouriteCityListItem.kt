package com.kprights.weatherapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kprights.weatherapp.databinding.FavouriteCityListItemBinding
import com.kprights.weatherapp.viewmodel.CityWeatherViewModel

/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 30/9/21
 * Time : 8:30 PM
 */

class FavouriteCityListItem(private val binding: FavouriteCityListItemBinding) : RecyclerView.ViewHolder(binding.root)
{
    fun bind(item: String, onClickListener: FavouriteCityListAdapter.OnClickListener) {
        binding.cityName = item
        binding.onClickListener = onClickListener
        addViewModel(item)
        binding.executePendingBindings()
    }

    private fun addViewModel(cityName: String){
        val model = CityWeatherViewModel()
        model.root.observeForever {
            binding.cityWeather = it
        }
        model.getWeatherByCityName(cityName = cityName)
    }

    companion object
    {
        fun from(parent: ViewGroup): FavouriteCityListItem {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FavouriteCityListItemBinding.inflate(layoutInflater, parent, false)
            return FavouriteCityListItem(binding)
        }
    }
}