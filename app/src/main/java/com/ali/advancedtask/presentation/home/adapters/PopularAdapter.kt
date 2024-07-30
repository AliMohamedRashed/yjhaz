package com.ali.advancedtask.presentation.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ali.advancedtask.R
import com.ali.advancedtask.databinding.CustomPopularLayoutBinding
import com.ali.advancedtask.model.PopularItem
import com.squareup.picasso.Picasso

class PopularAdapter(private var items: List<PopularItem>) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    inner class PopularViewHolder(private val binding: CustomPopularLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind( popularItem: PopularItem){
            Picasso.get()
                .load(popularItem.popularImage)
                .into(binding.customPopularIv)
            binding.customPopularItemName.text = popularItem.popularName
            binding.customPopularItemDistance.text = popularItem.popularDistance
            binding.categoryRate.text = popularItem.popularRate.toString()

            val  popularItemRating = popularItem.popularRate
            if(popularItemRating == 0.0) fillAndUnFillStarsFields(star1 = false, star2 = false, star3 = false, star4 = false, star5 = false)

            else if(popularItemRating <=1) fillAndUnFillStarsFields(star1 = true, star2 = false, star3 = false, star4 = false, star5 = false)

            else if(popularItemRating <= 2) fillAndUnFillStarsFields(star1 = true, star2 = true, star3 = false, star4 = false, star5 = false)

            else if(popularItemRating <= 3) fillAndUnFillStarsFields(star1 = true, star2 = true, star3 = true, star4 = false, star5 = false)

            else if(popularItemRating <= 4.5) fillAndUnFillStarsFields(star1 = true, star2 = true, star3 = true, star4 = true, star5 = false)

            else fillAndUnFillStarsFields(star1 = true, star2 = true, star3 = true, star4 = true, star5 = true)

        }
        private fun fillAndUnFillStarsFields(star1: Boolean, star2: Boolean, star3: Boolean, star4: Boolean, star5: Boolean){
            if(star1) binding.star1.setImageResource(R.drawable.ic_star_filled)
            else binding.star1.setImageResource(R.drawable.ic_star_not_filled)

            if(star2) binding.star2.setImageResource(R.drawable.ic_star_filled)
            else binding.star2.setImageResource(R.drawable.ic_star_not_filled)

            if(star3) binding.star3.setImageResource(R.drawable.ic_star_filled)
            else binding.star3.setImageResource(R.drawable.ic_star_not_filled)

            if(star4) binding.star4.setImageResource(R.drawable.ic_star_filled)
            else binding.star4.setImageResource(R.drawable.ic_star_not_filled)

            if(star5) binding.star5.setImageResource(R.drawable.ic_star_filled)
            else binding.star5.setImageResource(R.drawable.ic_star_not_filled)
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
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItems: List<PopularItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}


