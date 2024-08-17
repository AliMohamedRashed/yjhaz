package com.ali.advancedtask.core.extension_functions

import com.ali.advancedtask.core.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.asResult(): Flow<State<T>> {
    return this
        .map<T, State<T>> { State.Success(it) }
        .onStart { emit(State.Loading) }
        .catch { emit(State.Error(it)) }
}