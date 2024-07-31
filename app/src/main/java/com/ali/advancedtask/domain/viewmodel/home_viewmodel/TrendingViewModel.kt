package com.ali.advancedtask.domain.viewmodel.home_viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.domain.model.TrendingItems
import com.ali.advancedtask.data.repository.interfaces.TrendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val repository: TrendingRepository
) : ViewModel() {

    private val _trending = MutableLiveData(emptyList<TrendingItems>())
    val trending: LiveData<List<TrendingItems>> get() = _trending

    init {
        loadTrendingItems()
    }

    private fun loadTrendingItems() {
        viewModelScope.launch {
            val trendingItemsList = repository.getAllTrendingItems()
            _trending.postValue(trendingItemsList)
        }
    }
}