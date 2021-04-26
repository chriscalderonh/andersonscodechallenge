package com.chriscalderonh.andersonscodechallenge.searchacronym.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chriscalderonh.andersonscodechallenge.common.ui.adapterutils.BindableAdapter
import com.chriscalderonh.andersonscodechallenge.databinding.ViewLongformItemBinding
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.model.UiLongform
import com.chriscalderonh.andersonscodechallenge.searchacronym.ui.model.VmLongform
import javax.inject.Inject

class SearchAdapter @Inject constructor() :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>(), BindableAdapter<List<VmLongform>> {

    private var searchItems: List<VmLongform> = emptyList()

    override fun setData(data: List<VmLongform>?) {
        data?.let {
            searchItems = data
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  =
        ViewHolder(
            ViewLongformItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = searchItems.size

    inner class ViewHolder(private val binding: ViewLongformItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: VmLongform) {
            binding.item = item
        }
    }
}