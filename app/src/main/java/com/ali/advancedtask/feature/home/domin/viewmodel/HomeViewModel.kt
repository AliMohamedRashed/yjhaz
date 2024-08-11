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

    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = repository.getCategories()
                _state.value = _state.value.copy(
                    baseCategories = response.data!!,
                    success = response.success,
                    isLoading = false,
                    error = response.message
                )

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchPopularSellers() {
        viewModelScope.launch {
            try {
                val response = repository.getPopularSellers(29.1931,30.6421,1)
                _state.value = _state.value.copy(
                    popularSellers = response.data,
                    success = response.success,
                    isLoading = false,
                    error = response.message
                )

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchTrendingSellers() {
        viewModelScope.launch {
            try {
                val response = repository.getTrendingSellers(29.1931,30.6421,1)
                _state.value = _state.value.copy(
                    trendingSellers = response.data,
                    success = response.success,
                    isLoading = false,
                    error = response.message
                )

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}