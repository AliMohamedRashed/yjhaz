package com.ali.advancedtask.feature.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ali.advancedtask.databinding.CustomCategoryLayoutBinding
import com.ali.advancedtask.feature.home.data.model.response.base_categories.BaseCategoriesDataResponse
import com.squareup.picasso.Picasso

class CategoryAdapter(private var categories: List<BaseCategoriesDataResponse>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(private val binding: CustomCategoryLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: BaseCategoriesDataResponse) {
            Picasso.get()
                .load(category.image)
                .into(binding.customCategoryIv)
            binding.customCategoryTvCatName.text = category.nameEn
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CustomCategoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categories[position]
        holder.bind(item)
    }
}
