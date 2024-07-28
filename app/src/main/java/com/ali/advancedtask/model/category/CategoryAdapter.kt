package com.ali.advancedtask.model.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ali.advancedtask.databinding.CustomCategoryLayoutBinding

class CategoryAdapter(private val items: List<Categoty>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(private val binding: CustomCategoryLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind( category: Categoty){
            binding.customCategoryIv.setImageResource(category.catImage)
            binding.customCategoryTvCatName.setText(category.catName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CustomCategoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}