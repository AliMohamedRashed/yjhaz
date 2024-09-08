package com.ali.advancedtask.feature.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ali.advancedtask.databinding.CustomPopularLayoutBinding
import com.ali.advancedtask.feature.home.data.model.response.popular_sellers.PopularSellersDataResponse
import com.squareup.picasso.Picasso

class PopularAdapter(
    private var sellers: List<PopularSellersDataResponse>,
    private val onItemClicked: () -> Unit
    ) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = CustomPopularLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return sellers.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = sellers[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{
            onItemClicked()
        }
    }

    inner class PopularViewHolder(private val binding: CustomPopularLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(sellers: PopularSellersDataResponse){
            Picasso.get()
                .load(sellers.image)
                .into(binding.customPopularIv)
            binding.customPopularItemName.text = sellers.name
            binding.customPopularItemDistance.text = sellers.distance
            binding.categoryRate.text = sellers.rate
            binding.ratingBarSmall.rating = sellers.rate.toFloat()
        }
    }
}


