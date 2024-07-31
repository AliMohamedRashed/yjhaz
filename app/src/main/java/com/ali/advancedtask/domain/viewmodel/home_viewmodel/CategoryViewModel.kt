package com.ali.advancedtask.domain.viewmodel.home_viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.data.repository.interfaces.CategoriesRepository
import com.ali.advancedtask.domain.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoriesRepository
): ViewModel() {

    private val _categories = MutableLiveData(emptyList<Category>())
    val categories: LiveData<List<Category>> get() = _categories


    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            val categoriesList = repository.getAllCategories()
            _categories.postValue(categoriesList)
        }
    }
}