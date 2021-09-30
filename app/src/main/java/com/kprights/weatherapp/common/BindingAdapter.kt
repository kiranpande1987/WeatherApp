package com.kprights.weatherapp.common

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kprights.weatherapp.R
import com.kprights.weatherapp.view.CityListAdapter
import java.text.SimpleDateFormat
import java.util.*


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

@BindingAdapter("showImage")
fun showImage(imageViewForNewsFeed: ImageView, icon: String?) {
    val imageUrl = "http://openweathermap.org/img/wn/${icon}@4x.png"
    Log.e("IMAGGEE", imageUrl)

    imageUrl.let {
        val imgUri = imageUrl.toUri().buildUpon().scheme("http").build()

        Glide.with(imageViewForNewsFeed.context)
                .load(imgUri)
                .apply(
                        RequestOptions()
                                .placeholder(android.R.drawable.stat_sys_download)
                                .error(android.R.drawable.stat_notify_error)
                )
                .into(imageViewForNewsFeed)
    }
}