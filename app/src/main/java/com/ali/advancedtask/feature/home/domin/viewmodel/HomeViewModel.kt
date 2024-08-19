package com.ali.advancedtask.feature.home.domin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.core.State
import com.ali.advancedtask.feature.home.data.model.response.HomeDataResponseDto
import com.ali.advancedtask.feature.home.domin.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
): ViewModel() {

    private var _state = MutableStateFlow<State<HomeDataResponseDto>?>(null)
    val state = _state.asStateFlow()

    fun fetchCategoriesPopularTrendingData() {
        viewModelScope.launch {
            repository.getCategoriesPopularAndTrendingSellers().collect{ state->
                _state.value = state
            }
        }
    }
}