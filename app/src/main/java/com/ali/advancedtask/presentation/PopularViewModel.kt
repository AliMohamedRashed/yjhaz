package com.ali.advancedtask.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.model.popular.PopularItem
import com.ali.advancedtask.model.popular.PopularRepository
import kotlinx.coroutines.launch

class PopularViewModel: ViewModel() {

    private val _popular = MutableLiveData(emptyList<PopularItem>())
    val popular: LiveData<List<PopularItem>> get() = _popular

    private val repo = PopularRepository()

    init {
        loadPopularItems()
    }

    private fun loadPopularItems() {
        viewModelScope.launch {
            val trendingItemsList = repo.getAllPopularItems()
            _popular.postValue(trendingItemsList)
        }
    }
}