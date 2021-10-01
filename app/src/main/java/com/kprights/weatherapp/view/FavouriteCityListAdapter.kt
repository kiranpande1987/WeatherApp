package com.kprights.weatherapp.view

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 30/9/21
 * Time : 8:29 PM
 */

class FavouriteCityListAdapter(val onClickListener: OnClickListener): ListAdapter<String, FavouriteCityListItem>(NewsFeedDiffCallback())  //: RecyclerView.Adapter<NewsFeedListItem>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavouriteCityListItem.from(parent)

    override fun onBindViewHolder(holder: FavouriteCityListItem, position: Int) {
        val cityName = getItem(position)
        holder.bind(cityName, onClickListener)
    }

    class NewsFeedDiffCallback: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.contentEquals(newItem)
        }
    }

    class OnClickListener(val clickListener: (city: String) -> Unit) {
        fun onClick(city: String) = clickListener(city)
    }
}
