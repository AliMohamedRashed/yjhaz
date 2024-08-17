package com.ali.advancedtask.core

sealed interface State<out T> {
    data class Success<T>(val data: T) : State<T>
    data class Error(val exception: Throwable) : State<Nothing>
    object Loading : State<Nothing>
}