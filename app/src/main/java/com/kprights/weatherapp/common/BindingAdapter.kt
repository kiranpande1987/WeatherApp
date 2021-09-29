package com.kprights.weatherapp.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kprights.weatherapp.view.CityListAdapter


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/9/21
 * Time : 4:25 PM
 */

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<String>) {
    val adapter = recyclerView.adapter as CityListAdapter
    adapter.submitList(data)
}