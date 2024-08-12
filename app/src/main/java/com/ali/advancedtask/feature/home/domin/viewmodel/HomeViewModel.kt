package com.ali.advancedtask.feature.home.domin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.feature.home.domin.repository.HomeRepository
import com.ali.advancedtask.feature.home.domin.state.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
): ViewModel() {

    private var _state = MutableStateFlow(
        HomeScreenState(
            trendingSellers = emptyList() ,
            popularSellers = emptyList() ,
            baseCategories = emptyList() ,
            success = false,
            isLoading = true,
            error = null
        )
    )
    val state: StateFlow<HomeScreenState> = _state

    fun fetchCategoriesPopularTrendingData() {
        _state.value = _state.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val categoriesResponse = repository.getCategories()
                val popularSellersResponse = repository.getPopularSellers(29.1931, 30.6421, 1)
                val trendingSellersResponse = repository.getTrendingSellers(29.1931, 30.6421, 1)

                _state.value = _state.value.copy(
                    baseCategories = categoriesResponse.data!!,
                    popularSellers = popularSellersResponse.data,
                    trendingSellers = trendingSellersResponse.data,
                    success = categoriesResponse.success && popularSellersResponse.success && trendingSellersResponse.success,
                    error = categoriesResponse.message + popularSellersResponse.message + trendingSellersResponse.message
                )
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}