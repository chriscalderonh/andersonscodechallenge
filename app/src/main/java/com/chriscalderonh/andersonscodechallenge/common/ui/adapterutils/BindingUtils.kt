package com.chriscalderonh.andersonscodechallenge.common.ui.adapterutils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingUtils {

    @BindingAdapter("data")
    @JvmStatic
    fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T) {
        if (recyclerView.adapter is BindableAdapter<*>) {
            (recyclerView.adapter as BindableAdapter<T>).setData(data)
        }
    }
}