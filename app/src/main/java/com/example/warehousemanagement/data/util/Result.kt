package com.example.warehousemanagement.data.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable) : Result<Nothing>
    data object Loading : Result<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> = map<T, Result<T>> { Result.Success(it) }
    .onStart { emit(Result.Loading) }
    .catch {
        emit(Result.Error(it))
    }

fun <T> kotlin.Result<T>.asResult(): Result<T> {
    return when {
        isSuccess && getOrNull() != null -> Result.Success(requireNotNull(getOrNull()))
        isSuccess && getOrNull() == null -> Result.Error(RuntimeException("EmptyData"))
        else -> {
            Result.Error(exceptionOrNull() ?: RuntimeException())
        }
    }
}