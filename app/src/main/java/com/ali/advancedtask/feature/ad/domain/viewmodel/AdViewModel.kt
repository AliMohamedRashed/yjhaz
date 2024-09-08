package com.ali.advancedtask.feature.ad.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.advancedtask.core.State
import com.ali.advancedtask.feature.ad.data.model.AdDetails
import com.ali.advancedtask.feature.ad.domain.repository.AdRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdViewModel @Inject constructor(
    private val repository: AdRepository,
): ViewModel() {

    private var _state = MutableStateFlow<State<AdDetails>>(State.Loading)
    val state = _state.asStateFlow()

    init {
        getAdDetails()
    }

    private fun getAdDetails() {
        viewModelScope.launch {
            repository.getAdDetails().collect { state ->
                _state.value = state
            }
        }
    }

}