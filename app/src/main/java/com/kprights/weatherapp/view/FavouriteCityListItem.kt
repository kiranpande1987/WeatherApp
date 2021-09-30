package com.kprights.weatherapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kprights.weatherapp.databinding.CityCountryListItemBinding
import com.kprights.weatherapp.databinding.FavouriteCityListItemBinding


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
        binding.executePendingBindings()
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