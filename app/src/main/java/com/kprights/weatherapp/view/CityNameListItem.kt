package com.kprights.weatherapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kprights.weatherapp.databinding.CityCountryListItemBinding


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/9/21
 * Time : 4:07 PM
 */

class CityNameListItem(private val binding: CityCountryListItemBinding) : RecyclerView.ViewHolder(binding.root)
{
    fun bind(item: String, onClickListener: CityListAdapter.OnClickListener) {
        binding.cityName = item
        binding.onClickListener = onClickListener
        binding.executePendingBindings()
    }

    companion object
    {
        fun from(parent: ViewGroup): CityNameListItem {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CityCountryListItemBinding.inflate(layoutInflater, parent, false)
            return CityNameListItem(binding)
        }
    }
}