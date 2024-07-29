package com.ali.advancedtask.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.model.trending.TrendingItems
import com.ali.advancedtask.model.trending.TrendingRepository
import kotlinx.coroutines.launch

class TrendingViewModel : ViewModel() {

    private val _trending = MutableLiveData(emptyList<TrendingItems>())
    val trending: LiveData<List<TrendingItems>> get() = _trending

    private val repo = TrendingRepository()

    init {
        loadTrendingItems()
    }

    private fun loadTrendingItems() {
        viewModelScope.launch {
            val trendingItemsList = repo.getAllTrendingItems()
            _trending.postValue(trendingItemsList)
        }
    }
}