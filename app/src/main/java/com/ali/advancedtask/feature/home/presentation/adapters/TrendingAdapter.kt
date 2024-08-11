package com.ali.advancedtask.feature.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ali.advancedtask.databinding.CustomTrendingLayoutBinding
import com.ali.advancedtask.feature.home.data.model.response.trending_sellers.TrendingSellersDataResponse
import com.squareup.picasso.Picasso

class TrendingAdapter(private var sellers: List<TrendingSellersDataResponse>) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val binding = CustomTrendingLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingViewHolder(binding)    }

    override fun getItemCount(): Int {
        return sellers.size
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val item = sellers[position]
        holder.bind(item)
    }

    inner class TrendingViewHolder(private val binding: CustomTrendingLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trendingSellers: TrendingSellersDataResponse) {
            Picasso.get()
                .load(trendingSellers.image)
                .into(binding.customTrendingIv)
        }
    }

}