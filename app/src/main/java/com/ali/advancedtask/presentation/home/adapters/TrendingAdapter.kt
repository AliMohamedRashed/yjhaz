package com.ali.advancedtask.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ali.advancedtask.databinding.CustomTrendingLayoutBinding
import com.ali.advancedtask.domain.model.TrendingItems
import com.squareup.picasso.Picasso

class TrendingAdapter(private var items: List<TrendingItems>) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>()  {

    inner class TrendingViewHolder(private val binding: CustomTrendingLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trendingItems: TrendingItems) {
            Picasso.get()
                .load(trendingItems.trendingImage)
                .into(binding.customTrendingIv)
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
    fun updateData(newItems: List<TrendingItems>) {
        items = newItems
        notifyDataSetChanged()
    }
}