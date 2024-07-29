package com.ali.advancedtask.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.model.category.CategoriesRepository
import com.ali.advancedtask.model.category.Category
import kotlinx.coroutines.launch

class CategoryViewModel: ViewModel() {

    private val _categories = MutableLiveData(emptyList<Category>())
    val categories: LiveData<List<Category>> get() = _categories

    private val repo = CategoriesRepository()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            val categoriesList = repo.getAllCategories()
            _categories.postValue(categoriesList)
        }
    }
}