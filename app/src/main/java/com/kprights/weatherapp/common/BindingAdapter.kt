package com.kprights.weatherapp.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kprights.weatherapp.R
import com.kprights.weatherapp.viewmodel.ApiStatus


/**
 * Copyright (c) 2021 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/9/21
 * Time : 4:25 PM
 */

@BindingAdapter("showImage")
fun showImage(imageView: ImageView, icon: String?) {
    val imageUrl = "http://openweathermap.org/img/wn/${icon}@4x.png"

    imageUrl.let {
        val imgUri = imageUrl.toUri().buildUpon().scheme("http").build()

        Glide.with(imageView.context)
                .load(imgUri)
                .apply(
                        RequestOptions()
                                .placeholder(R.drawable.loading_img)
                                .error(android.R.drawable.stat_notify_error)
                )
                .into(imageView)
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
        ApiStatus.NO_INTERNET -> {
            statusImageView.visibility = View.GONE
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}