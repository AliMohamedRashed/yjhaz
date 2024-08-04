package com.ali.advancedtask.feature.home.domin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.feature.home.domin.repository.CategoriesRepository
import com.ali.advancedtask.feature.home.data.model.response.PopularItem
import com.ali.advancedtask.feature.home.domin.repository.PopularRepository
import com.ali.advancedtask.feature.home.domin.repository.TrendingRepository
import com.ali.advancedtask.feature.home.data.model.response.Category
import com.ali.advancedtask.feature.home.data.model.response.TrendingItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val popularRepository: PopularRepository,
    private val categoriesRepository: CategoriesRepository,
    private val trendingRepository: TrendingRepository
) : ViewModel() {

    private val _popular = MutableLiveData(emptyList<PopularItem>())
    val popular: LiveData<List<PopularItem>> get() = _popular

    private val _categories = MutableLiveData(emptyList<Category>())
    val categories: LiveData<List<Category>> get() = _categories

    private val _trending = MutableLiveData(emptyList<TrendingItems>())
    val trending: LiveData<List<TrendingItems>> get() = _trending

    init {
        loadPopularItems()
        loadCategories()
        loadTrendingItems()
    }

    private fun loadPopularItems() {
        viewModelScope.launch {
            val trendingItemsList = popularRepository.getAllPopularItems()
            _popular.postValue(trendingItemsList)
        }
    }



    private fun loadCategories() {
        viewModelScope.launch {
            val categoriesList = categoriesRepository.getAllCategories()
            _categories.postValue(categoriesList)
        }
    }




    private fun loadTrendingItems() {
        viewModelScope.launch {
            val trendingItemsList = trendingRepository.getAllTrendingItems()
            _trending.postValue(trendingItemsList)
        }
    }
}