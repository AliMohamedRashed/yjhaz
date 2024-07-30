package com.ali.advancedtask.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.ali.advancedtask.databinding.CustomCategoryLayoutBinding
import com.ali.advancedtask.model.Category

class CategoryAdapter(private var items: List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(private val binding: CustomCategoryLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        //getting image from database using coil library
        fun bind(category: Category) {
            val imageLoader = ImageLoader.Builder(binding.customCategoryIv.context)
                .components {
                    add(SvgDecoder.Factory())
                }
                .build()
            val request = ImageRequest.Builder(binding.customCategoryIv.context)
                .data(category.catImage)
                .target(binding.customCategoryIv)
                .build()

            imageLoader.enqueue(request)
            binding.customCategoryTvCatName.text = category.catName
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

    fun updateData(newItems: List<Category>) {
        items = newItems
        notifyDataSetChanged()
    }
}
