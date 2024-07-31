package com.ali.advancedtask.domain.viewmodel.home_viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.domain.model.PopularItem
import com.ali.advancedtask.data.PopularRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val repository: PopularRepository
) : ViewModel() {

    private val _popular = MutableLiveData(emptyList<PopularItem>())
    val popular: LiveData<List<PopularItem>> get() = _popular

    init {
        loadPopularItems()
    }

    private fun loadPopularItems() {
        viewModelScope.launch {
            val trendingItemsList = repository.getAllPopularItems()
            _popular.postValue(trendingItemsList)
        }
    }
}