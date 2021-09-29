package com.kprights.weatherapp.view

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/9/21
 * Time : 4:02 PM
 */

class CityListAdapter(val onClickListener: OnClickListener): ListAdapter<String, CityNameListItem>(NewsFeedDiffCallback())  //: RecyclerView.Adapter<NewsFeedListItem>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CityNameListItem.from(parent)

    override fun onBindViewHolder(holder: CityNameListItem, position: Int) {
        val news = getItem(position)
        holder.bind(news, onClickListener)
    }

    class NewsFeedDiffCallback: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: (city: String) -> Unit) {
        fun onClick(city: String) = clickListener(city)
    }
}
