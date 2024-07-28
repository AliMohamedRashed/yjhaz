package com.ali.advancedtask.model.trending

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ali.advancedtask.databinding.CustomTrendingLayoutBinding

class TrendingAdapter(private val items: List<TrendingItems>) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>()  {

    inner class TrendingViewHolder(private val binding: CustomTrendingLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trendingItems: TrendingItems) {
            binding.customTrendingIv.setImageResource(trendingItems.trendingImage)
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

}