package com.ali.advancedtask.model.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ali.advancedtask.databinding.CustomPopularLayoutBinding
import com.squareup.picasso.Picasso

class PopularAdapter(private var items: List<PopularItem>) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    inner class PopularViewHolder(private val binding: CustomPopularLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind( popularItem: PopularItem){
            Picasso.get()
                .load(popularItem.popularImage)
                .into(binding.customPopularIv)
            binding.customPopularItemName.setText(popularItem.popularName)
            binding.customPopularItemDistance.setText(popularItem.popularDistance)
        }
    }

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
    fun updateData(newItems: List<PopularItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}


