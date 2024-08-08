package com.ali.advancedtask.feature.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ali.advancedtask.databinding.CustomTrendingLayoutBinding
import com.squareup.picasso.Picasso

class TrendingAdapter(private var items: List<String>) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>()  {

    inner class TrendingViewHolder(private val binding: CustomTrendingLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trendingItems: String) {
//            Picasso.get()
//                .load(trendingItems.trendingImage)
//                .into(binding.customTrendingIv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val binding = CustomTrendingLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingViewHolder(binding)    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
    fun updateData(newItems: List<String>) {
        items = newItems
        notifyDataSetChanged()
    }
}