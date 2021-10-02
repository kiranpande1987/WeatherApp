package com.kprights.weatherapp.common

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kprights.weatherapp.R
import com.kprights.weatherapp.viewmodel.ApiStatus
import java.util.*


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/9/21
 * Time : 4:25 PM
 */

@BindingAdapter("showImage")
fun showImage(imageViewForNewsFeed: ImageView, icon: String?) {
    val imageUrl = "http://openweathermap.org/img/wn/${icon}@4x.png"

    imageUrl.let {
        val imgUri = imageUrl.toUri().buildUpon().scheme("http").build()

        Glide.with(imageViewForNewsFeed.context)
                .load(imgUri)
                .apply(
                        RequestOptions()
                                .placeholder(R.drawable.loading_img)
                                .error(android.R.drawable.stat_notify_error)
                )
                .into(imageViewForNewsFeed)
    }
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        ApiStatus.ERROR -> {
            statusImageView.visibility = View.GONE
        }

        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}