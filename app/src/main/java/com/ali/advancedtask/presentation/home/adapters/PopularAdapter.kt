package com.ali.advancedtask.presentation.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ali.advancedtask.R
import com.ali.advancedtask.databinding.CustomPopularLayoutBinding
import com.ali.advancedtask.domain.model.PopularItem
import com.squareup.picasso.Picasso

class PopularAdapter(private var items: List<PopularItem>) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = CustomPopularLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItems: List<PopularItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class PopularViewHolder(private val binding: CustomPopularLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind( popularItem: PopularItem){
            Picasso.get()
                .load(popularItem.popularImage)
                .into(binding.customPopularIv)
            binding.customPopularItemName.text = popularItem.popularName
            binding.customPopularItemDistance.text = popularItem.popularDistance
            binding.categoryRate.text = popularItem.popularRate.toString()

            val  popularItemRating = popularItem.popularRate
            binding.ratingBarSmall.rating = popularItemRating.toFloat()
        }
    }
}


